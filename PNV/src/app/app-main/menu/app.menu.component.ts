import {Component, Input, OnInit} from '@angular/core';
import {trigger, state, style, transition, animate} from '@angular/animations';
import {Location} from '@angular/common';
import {Router} from '@angular/router';
import {MenuItem} from 'primeng/primeng';
import {AppMainComponent} from '../app.main.component';

@Component({
    selector: 'app-menu',
    template: `
            <div class="menu-scroll-content">
            <ul app-submenu [item]="model" root="true" class="navigation-menu" visible="true" parentActive="true"></ul>
            </div>
            `
})
export class AppMenuComponent implements OnInit {

    public model: any[];
    public permissoes: any[] = []; 

    constructor(public app: AppMainComponent) {
        
    }

    indicadores: any[];
    gerencias: any[];
    usuPerformance: boolean = false;
    usuComissao: boolean = false;
    usuTransporte: boolean = false;
    usuJuridicoPagamentos: boolean = false;
    usuProjetos: boolean = false;
    admSispc: boolean = false;
    admPerformance: boolean = false;
    usuJuridicoPagamentosAprovacao: boolean = false;

    

    ngOnInit() {

        //Preencehendo array de permissoes e liberando acessos
        let i =0
        while (sessionStorage.getItem("permissao "+ i) != null){
            let permissao = sessionStorage.getItem("permissao "+ i)
            this.permissoes.push(permissao)
            //Liberando acessos
            if(permissao === "ROLE_ADMIN"){
                this.usuPerformance = true
                this.usuComissao = true
                this.usuTransporte = true
            }
            
            i++
        }

        console.log(this.usuPerformance)


            this.model=[]

        //Começando a construção do Menu

        //Performance
        if(this.usuPerformance === true){
            this.model.push( 
                {
                    label: 'Performance', icon: 'timeline',
                    
                    items: this.permissoes[1] === "ROLE_DESENVOLVIMENTO" ?  //DEntro do operador o que ainda será construído
                    [
                        {
                            label: 'Acompanhamento', icon: 'subject',
                            items: this.gerencias
                            
                        },
                        {
                            label: 'Fechamento', icon: 'subject',
                            items: [
                                {label: 'Relatórios por área', icon: 'subject'},
                                {label: 'Arquivamento', icon: 'subject'}
                            ]
                        },
                        {
                            label: 'Configurações', icon: 'subject',
                            items: [
                                {label: 'Indicadores', icon: 'subject', routerLink: 'indicadoresAdmin'}
                            ]
                        }
                    ] :
                    this.admPerformance === true ?  //DEntro do operador o que ainda será construído
                    [
                        {
                            label: 'Acompanhamento', icon: 'subject',
                            items: this.gerencias
                            
                        },
                        {
                            label: 'Configurações', icon: 'settings',
                            items: [
                                {label: 'Indicadores', icon: 'build', routerLink: 'indicadoresAdmin'}
                            ]
                        }
                    ] :
                    [
                        {
                            label: 'Acompanhamento', icon: 'subject',
                            items: this.gerencias
                            
                        }  
                    ]
                });
            }
           
       
        
        }
    //fechando subscribe de gerencia


}

@Component({
    /* tslint:disable:component-selector */
    selector: '[app-submenu]',
    /* tslint:enable:component-selector */
    template: `
        <ng-template ngFor let-child let-i="index" [ngForOf]="(root ? item : item.items)">
            <li [ngClass]="{'active-menuitem': isActive(i)}" [class]="child.badgeStyleClass" *ngIf="child.visible === false ? false : true">
                <a [href]="child.url||'#'" (click)="itemClick($event,child,i)" class="ripplelink"
                   *ngIf="!child.routerLink" [attr.tabindex]="!visible ? '-1' : null" [attr.target]="child.target"
                    (mouseenter)="hover=true" (mouseleave)="hover=false">
                    <i class="material-icons">{{child.icon}}</i>
                    <span>{{child.label}}</span>
                    <span class="ink" *ngIf="hover"></span>
                    <span class="menuitem-badge" *ngIf="child.badge">{{child.badge}}</span>
                    <i class="material-icons" *ngIf="child.items">keyboard_arrow_down</i>
                </a>

                <a (click)="itemClick($event,child,i)" class="ripplelink" *ngIf="child.routerLink"
                    [routerLink]="child.routerLink" routerLinkActive="active-menuitem-routerlink"
                   [routerLinkActiveOptions]="{exact: true}" [attr.tabindex]="!visible ? '-1' : null" [attr.target]="child.target"
                    (mouseenter)="hover=true" (mouseleave)="hover=false">
                    <i class="material-icons">{{child.icon}}</i>
                    <span>{{child.label}}</span>
                    <span class="ink" *ngIf="hover"></span>
                    <span class="menuitem-badge" *ngIf="child.badge">{{child.badge}}</span>
                    <i class="material-icons" *ngIf="child.items">keyboard_arrow_down</i>
                </a>
                <ul app-submenu [item]="child" *ngIf="child.items" [parentActive]="isActive(i)" [@children]="isActive(i) ?
                'visible' : 'hidden'" [visible]="isActive(i)"></ul>
            </li>
        </ng-template>
    `,
    animations: [
        trigger('children', [
            state('hidden', style({
                height: '0px'
            })),
            state('visible', style({
                height: '*'
            })),
            transition('visible => hidden', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)')),
            transition('hidden => visible', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)'))
        ])
    ]
})
export class AppSubMenuComponent {

    @Input() item: MenuItem;

    @Input() root: boolean;

    @Input() visible: boolean;

    _parentActive: boolean;

    activeIndex: number;

    hover: boolean;

    constructor(public app: AppMainComponent, public router: Router, public location: Location) {}

    itemClick(event: Event, item: MenuItem, index: number) {
        // avoid processing disabled items
        if (item.disabled) {
            event.preventDefault();
            return true;
        }

        // activate current item and deactivate active sibling if any
        this.activeIndex = (this.activeIndex === index) ? null : index;

        // execute command
        if (item.command) {
            item.command({originalEvent: event, item});
        }

        // prevent hash change
        if (item.items || (!item.url && !item.routerLink)) {
            event.preventDefault();
        }

        // hide menu
        if (!item.items && this.app.overlay) {
            this.app.sidebarActive = false;
        }
    }

    isActive(index: number): boolean {
        return this.activeIndex === index;
    }

    unsubscribe(item: any) {
        if (item.eventEmitter) {
            item.eventEmitter.unsubscribe();
        }

        if (item.items) {
            for (const childItem of item.items) {
                this.unsubscribe(childItem);
            }
        }
    }

    @Input() get parentActive(): boolean {
        return this._parentActive;
    }

    set parentActive(val: boolean) {
        this._parentActive = val;

        if (!this._parentActive) {
            this.activeIndex = null;
        }
    }
}
