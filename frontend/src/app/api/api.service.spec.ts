import { TestBed } from '@angular/core/testing';

import { ApiService } from './api.service';

describe('ApiService', () => {

  let service: ApiService;
 
  // AAA
  // Arrange 
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ApiService],
    });
    service = TestBed.inject(ApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('Metodo recupera pasta - Expected: Status: 404', async () => {

    //Act
    let response = await service.getPasta(0, 0);

    // Assert
    expect((response as any).status).toEqual(404);

  });
});
