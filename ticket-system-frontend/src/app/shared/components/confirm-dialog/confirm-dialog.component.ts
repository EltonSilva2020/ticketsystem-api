// confirm-dialog.component.ts
import { Component } from '@angular/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-confirm-dialog',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule],
  template: `
    <h2 mat-dialog-title>Tem certeza?</h2>
    <mat-dialog-content>Esta ação não poderá ser desfeita.</mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button mat-dialog-close="cancel">Cancelar</button>
      <button mat-button color="warn" mat-dialog-close="confirm">Confirmar</button>
    </mat-dialog-actions>
  `
})
export class ConfirmDialogComponent {}
