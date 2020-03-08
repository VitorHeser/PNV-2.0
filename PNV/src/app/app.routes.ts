import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders, NgModule } from '@angular/core';
import { DashboardDemoComponent } from './demo/view/dashboarddemo.component';
import { SampleDemoComponent } from './demo/view/sampledemo.component';
import { FormsDemoComponent } from './demo/view/formsdemo.component';
import { DataDemoComponent } from './demo/view/datademo.component';
import { PanelsDemoComponent } from './demo/view/panelsdemo.component';
import { OverlaysDemoComponent } from './demo/view/overlaysdemo.component';
import { MenusDemoComponent } from './demo/view/menusdemo.component';
import { MessagesDemoComponent } from './demo/view/messagesdemo.component';
import { MiscDemoComponent } from './demo/view/miscdemo.component';
import { EmptyDemoComponent } from './demo/view/emptydemo.component';
import { ChartsDemoComponent } from './demo/view/chartsdemo.component';
import { FileDemoComponent } from './demo/view/filedemo.component';
import { UtilsDemoComponent } from './demo/view/utilsdemo.component';
import { DocumentationComponent } from './demo/view/documentation.component';
import { AppMainComponent } from './app-main/app.main.component';
import { AppNotfoundComponent } from './pages/app.notfound.component';
import { AppErrorComponent } from './pages/app.error.component';
import { AppAccessdeniedComponent } from './pages/app.accessdenied.component';
import { AppLoginComponent } from './pages/app.login.component';
import { HomeComponent } from './home/home.component';
import { CadastrarComponent } from './tarefas/cadastrar/cadastrar.component';
import { AmbientealunoComponent } from './ambientealuno/ambientealuno.component';

import { TarefaRoutes } from './tarefas';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './guards/auth.guard.service';
import { AdminComponent } from './admin/admin.component';




export const routes: Routes = [
  /*  {
		path: 'tarefas',
		redirectTo: 'tarefas/listar',
		pathMatch: 'full'
	},
	...TarefaRoutes,*/
    { path: '', component: AppMainComponent,
        children: [
            { path: '', component: HomeComponent, },
            { path: 'dash', component: DashboardDemoComponent, canActivate: [AuthGuard] },
            { path: 'sample', component: SampleDemoComponent, canActivate: [AuthGuard] },
            { path: 'forms', component: FormsDemoComponent, canActivate: [AuthGuard] },
            { path: 'data', component: DataDemoComponent, canActivate: [AuthGuard] },
            { path: 'panels', component: PanelsDemoComponent, canActivate: [AuthGuard] },
            { path: 'overlays', component: OverlaysDemoComponent, canActivate: [AuthGuard] },
            { path: 'menus', component: MenusDemoComponent, canActivate: [AuthGuard] },
            { path: 'messages', component: MessagesDemoComponent, canActivate: [AuthGuard] },
            { path: 'misc', component: MiscDemoComponent, canActivate: [AuthGuard] },
            { path: 'empty', component: EmptyDemoComponent, canActivate: [AuthGuard] },
            { path: 'charts', component: ChartsDemoComponent, canActivate: [AuthGuard] },
            { path: 'file', component: FileDemoComponent, canActivate: [AuthGuard] },
            { path: 'utils', component: UtilsDemoComponent, canActivate: [AuthGuard] },
            { path: 'documentation', component: DocumentationComponent, canActivate: [AuthGuard] },
            { path: 'cadastrar', component: CadastrarComponent, canActivate: [AuthGuard] },
            { path: 'ambientealuno', component: AmbientealunoComponent},
            { path: 'admin', component: AdminComponent, canActivate: [AuthGuard]}
        ]
    },
    { path: 'error', component: AppErrorComponent, canActivate: [AuthGuard]},
    { path: 'accessdenied', component: AppAccessdeniedComponent, canActivate: [AuthGuard]},
    { path: '404', component: AppNotfoundComponent, canActivate: [AuthGuard]},
    { path: 'login', component: LoginComponent },
    { path: '**', redirectTo: '/404'},
	//...TarefaRoutes
    

];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled'});

@NgModule({
	imports: [ RouterModule.forRoot(routes) ],

exports: [ RouterModule ]
})
export class AppRoutingModule {}
