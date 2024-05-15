import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeroFormComponent } from './hero-form/hero-form.component';
import { HomeComponent } from './housing/housing.component';

@NgModule({
  imports: [ //Import certain moduls like FormsModule and ReactiveFormsModule
    BrowserModule,
    CommonModule,
    FormsModule
  ],
  declarations: [ 
    //Declare all you component in declarations - 
    AppComponent,
    HeroFormComponent,
    HomeComponent
  ],
  providers: [],
  bootstrap: [ AppComponent ] //Always bootstrap AppComponent.
})
export class AppModule { }
