import { Component, OnInit } from '@angular/core';
import { GeneroService } from '../service/genero.service';
import { Genero } from '../model/genero';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-genero',
  templateUrl: './genero.page.html',
  styleUrls: ['./genero.page.scss'],
})
export class GeneroPage implements OnInit {

  generos: Genero[];

  constructor(private generoService: GeneroService,
              private router: Router) { }

  ngOnInit() {
    this.findAll();
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.findAll();
    });
  }

  findAll(): void {
    this.generoService.findAll().subscribe(e => this.generos = e);
  }

  edit(id: number): void {
    this.router.navigate([`/genero/${id}`]);
  }

  delete(id: number): void {
    this.generoService.delete(id).subscribe(() => {
      this.findAll();
    });
  }
}
