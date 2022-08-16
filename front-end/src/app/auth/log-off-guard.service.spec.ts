import { TestBed } from '@angular/core/testing';

import { LogOffGuardService } from './log-off-guard.service';

describe('LogOffGuardService', () => {
  let service: LogOffGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LogOffGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
