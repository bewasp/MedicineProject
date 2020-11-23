import { TestBed, inject } from '@angular/core/testing';

import { ClientCuresService } from './client-cures.service';

describe('ClientCuresService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ClientCuresService]
    });
  });

  it('should be created', inject([ClientCuresService], (service: ClientCuresService) => {
    expect(service).toBeTruthy();
  }));
});
