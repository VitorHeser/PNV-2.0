import {Component, AfterViewInit, Renderer2, OnDestroy, OnInit, NgZone, EventEmitter, Output} from '@angular/core';
import { AuthService } from '../login/auth.service';
import { Router } from '@angular/router';

enum MenuOrientation {
    STATIC,
    OVERLAY
}

@Component({
    selector: 'app-main',
    templateUrl: './app.main.component.html'
})
export class AppMainComponent implements AfterViewInit, OnDestroy, OnInit {

    @Output() header = new EventEmitter
    mostrarMenu: boolean = false;
    barraAtiva: boolean = true;

    activeTabIndex: number = 0;

    sidebarActive: boolean = false;

    layoutMode: MenuOrientation = MenuOrientation.STATIC;

    darkMenu = true;

    topbarMenuActive: boolean;

    sidebarClick: boolean;

    topbarItemClick: boolean;

    activeTopbarItem: any;

    documentClickListener: any;

    rippleInitListener: any;

    rippleMouseDownListener: any;

    usuAluno: boolean;
    usuTutor: boolean;
    permissoes: any[] = []
    constructor(
        public renderer: Renderer2,
        public zone: NgZone,
        private authService: AuthService,
        private router: Router,
        ) {

        
    }

        

    ngOnInit() {
        this.zone.runOutsideAngular(() => {this.bindRipple(); });
        this.authService.mostrarMenuEmitter.subscribe(
            mostrar =>{ 
              this.mostrarMenu = mostrar;
            })
            
    }

    activeMenu(event) {

        let node;
        if (event.target.tagName === "A") {
          node = event.target;
        } else {
          node = event.target.parentNode;
        }
        let menuitem = document.getElementsByClassName("ui-menuitem-link");
        for (let i = 0; i < menuitem.length; i++) {
          menuitem[i].classList.remove("active");
        }
        node.classList.add("active")
      }

    bindRipple() {
        this.rippleInitListener = this.init.bind(this);
        document.addEventListener('DOMContentLoaded', this.rippleInitListener);
    }

    init() {
        this.rippleMouseDownListener = this.rippleMouseDown.bind(this);
        document.addEventListener('mousedown', this.rippleMouseDownListener, false);
    }

    rippleMouseDown(e) {
        const parentNode = 'parentNode';
        for (let target = e.target; target && target !== this; target = target[parentNode]) {
            if (!this.isVisible(target)) {
                continue;
            }

            // Element.matches() -> https://developer.mozilla.org/en-US/docs/Web/API/Element/matches
            if (this.selectorMatches(target, '.ripplelink, .ui-button, .ui-listbox-item, .ui-multiselect-item, .ui-fieldset-toggler')) {
                const element = target;
                this.rippleEffect(element, e);
                break;
            }
        }
    }

    selectorMatches(el, selector) {
        const matches = 'matches';
        const webkitMatchesSelector = 'webkitMatchesSelector';
        const mozMatchesSelector = 'mozMatchesSelector';
        const msMatchesSelector = 'msMatchesSelector';
        const p = Element.prototype;
        const f = p[matches] || p[webkitMatchesSelector] || p[mozMatchesSelector] || p[msMatchesSelector] || function(s) {
            return [].indexOf.call(document.querySelectorAll(s), this) !== -1;
        };
        return f.call(el, selector);
    }

    isVisible(el) {
        return !!(el.offsetWidth || el.offsetHeight);
    }

    rippleEffect(element, e) {
        if (element.querySelector('.ink') === null) {
            const inkEl = document.createElement('span');
            this.addClass(inkEl, 'ink');

            if (this.hasClass(element, 'ripplelink') && element.querySelector('span')) {
                element.querySelector('span').insertAdjacentHTML('afterend', '<span class=\'ink\'></span>');
            } else {
                element.appendChild(inkEl);
            }
        }

        const ink = element.querySelector('.ink');
        this.removeClass(ink, 'ripple-animate');

        if (!ink.offsetHeight && !ink.offsetWidth) {
            const d = Math.max(element.offsetWidth, element.offsetHeight);
            ink.style.height = d + 'px';
            ink.style.width = d + 'px';
        }

        const x = e.pageX - this.getOffset(element).left - (ink.offsetWidth / 2);
        const y = e.pageY - this.getOffset(element).top - (ink.offsetHeight / 2);

        ink.style.top = y + 'px';
        ink.style.left = x + 'px';
        ink.style.pointerEvents = 'none';
        this.addClass(ink, 'ripple-animate');
    }
    hasClass(element, className) {
        if (element.classList) {
            return element.classList.contains(className);
        } else {
            return new RegExp('(^| )' + className + '( |$)', 'gi').test(element.className);
        }
    }

    addClass(element, className) {
        if (element.classList) {
            element.classList.add(className);
        } else {
            element.className += ' ' + className;
        }
    }

    removeClass(element, className) {
        if (element.classList) {
            element.classList.remove(className);
        } else {
            element.className = element.className.replace(new RegExp('(^|\\b)' + className.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
        }
    }

    getOffset(el) {
        const rect = el.getBoundingClientRect();

        return {
            top: rect.top + (window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0),
            left: rect.left + (window.pageXOffset || document.documentElement.scrollLeft || document.body.scrollLeft || 0),
        };
    }

    unbindRipple() {
        if (this.rippleInitListener) {
            document.removeEventListener('DOMContentLoaded', this.rippleInitListener);
        }
        if (this.rippleMouseDownListener) {
            document.removeEventListener('mousedown', this.rippleMouseDownListener);
        }
    }

    ngAfterViewInit() {
        this.documentClickListener = this.renderer.listen('body', 'click', (event) => {
            if (!this.topbarItemClick) {
                this.activeTopbarItem = null;
                this.topbarMenuActive = false;
            }

            if (!this.sidebarClick && (this.overlay || !this.isDesktop())) {
                this.sidebarActive = false;
            }

            this.topbarItemClick = false;
            this.sidebarClick = false;
        });
    }

    desativaBarra(){
        this.barraAtiva = !this.barraAtiva;
        console.log(this.barraAtiva)
      }

    onTabClick(event: Event, index: number) {
        if (this.activeTabIndex === index) {
            this.sidebarActive = !this.sidebarActive;
        } else {
            this.activeTabIndex = index;
            this.sidebarActive = true;
        }

        event.preventDefault();
    }

    closeSidebar(event: Event) {
        this.sidebarActive = false;
        event.preventDefault();
    }

    onSidebarClick($event) {
        this.sidebarClick = true;
    }

    onTopbarMenuButtonClick(event) {
        this.topbarItemClick = true;
        this.topbarMenuActive = !this.topbarMenuActive;

        event.preventDefault();
    }

    onTopbarItemClick(event, item) {
        this.topbarItemClick = true;

        if (this.activeTopbarItem === item) {
            this.activeTopbarItem = null; } else {
            this.activeTopbarItem = item; }

        event.preventDefault();
    }

    onTopbarSubItemClick(event) {
        event.preventDefault();
    }

    get overlay(): boolean {
        return this.layoutMode === MenuOrientation.OVERLAY;
    }

    changeToStaticMenu() {
        this.layoutMode = MenuOrientation.STATIC;
    }

    changeToOverlayMenu() {
        this.layoutMode = MenuOrientation.OVERLAY;
    }

    isDesktop() {
        return window.innerWidth > 1024;
    }

    ngOnDestroy() {
        if (this.documentClickListener) {
            this.documentClickListener();
        }
        this.unbindRipple();
    }
}
