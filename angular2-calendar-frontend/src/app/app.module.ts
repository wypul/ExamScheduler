import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import {CalendarComponent} from "./calendar/calendar.component";
import {DayPilot} from "daypilot-pro-angular";
import {DataService} from "./backend/data.service";
import {CreateComponent} from "./dialogs/create.component";

@NgModule({
  declarations: [
    AppComponent,
    CalendarComponent,
    CreateComponent,
    DayPilot.Angular.Calendar,
    DayPilot.Angular.Modal,
    DayPilot.Angular.Navigator
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpModule
  ],
  providers: [
    DataService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
