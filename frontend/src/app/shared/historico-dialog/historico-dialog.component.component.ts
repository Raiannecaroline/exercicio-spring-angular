import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ApiService } from 'src/app/api/api.service';
import Historico from 'src/app/models/Historico';

@Component({
  selector: 'app-historico-dialog.component',
  templateUrl: './historico-dialog.component.component.html',
  styleUrls: ['./historico-dialog.component.component.sass']
})
export class HistoricoDialogComponentComponent implements OnInit {

  constructor(
    private api: ApiService,
    @Inject(MAT_DIALOG_DATA) public data: Historico[],
    public dialogRef: MatDialogRef<HistoricoDialogComponentComponent>
  ) {
  }

  ngOnInit(): void {

  }

}
