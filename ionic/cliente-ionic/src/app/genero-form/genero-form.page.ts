import { Component, OnInit, NgZone } from '@angular/core';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Genero } from '../model/genero';
import { GeneroService } from '../service/genero.service';

@Component({
  selector: 'app-genero-form',
  templateUrl: './genero-form.page.html',
  styleUrls: ['./genero-form.page.scss'],
})
export class GeneroFormPage implements OnInit {

  crudForm: FormGroup;
  generoEdit: Genero;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder,
              private generoService: GeneroService,
              private zone: NgZone) {
    this.crudForm = this.formBuilder.group({
      nome: [null, Validators.required]
    });
  }

  ngOnInit() {
    this.generoEdit = new Genero();
    const id = Number(this.route.snapshot.params.id);
    if (id) {
      this.generoService.findOne(id).subscribe(e => this.generoEdit = e);
    }
  }

  save() {
    this.generoService.save(this.generoEdit).subscribe(() => {
      this.router.navigate(['/genero']);
    });
  }

}
