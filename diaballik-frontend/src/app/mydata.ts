import { Injectable } from '@angular/core';

// This class is data that will be used through the different components
// by dependency injection.
// See mycomponent.component.ts for a usage of this class.
// See app.module.ts to see how this injection is configured
@Injectable()
export class MyData {
  // The data to store
  // any: https://www.typescriptlang.org/docs/handbook/basic-types.html
  public storage: any;
  public test: any;

  public constructor() {
  }

  public setTest(d) {
    this.test = d;
  }

  public setStorage(data) {
    this.storage = data;
  }
}
