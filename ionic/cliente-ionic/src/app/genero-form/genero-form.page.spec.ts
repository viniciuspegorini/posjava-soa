import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneroFormPage } from './genero-form.page';

describe('GeneroFormPage', () => {
  let component: GeneroFormPage;
  let fixture: ComponentFixture<GeneroFormPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GeneroFormPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GeneroFormPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
