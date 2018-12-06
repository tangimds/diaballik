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

  public constructor() {
    // An example of how storage can be used to store json data.
    // The JSON class can both parse JSON from a string, a produce a string from a JSON object (stringify)
    this.storage  = JSON.parse('{' +
      '"p1":{"type":"HumanPlayer","@id":1,"name":"jean","color":"WHITE"},' +
      // '"p2":{"type":"HumanPlayer","@id":2,"name":"yves","color":"BLACK"},' +
      '"p2":{"type":"AIPlayer","@id":2,"name":null,"color":"BLACK","difficulty":"NOOB"},' +
      '"scenario":"STANDARD","nbTurn":2,"id":1544048964387,' +
      '"actions":[{"type":"MovePiece","piece":{"type":"Piece","color":"WHITE","x":1,"y":2},"dx":0,"dy":1},' +
      '{"type":"MovePiece","piece":{"type":"Piece","color":"WHITE","x":3,"y":2},"dx":0,"dy":1},' +
      '{"type":"MovePiece","piece":{"type":"Piece","color":"WHITE","x":3,"y":2},"dx":1,"dy":0},' +
      '{"type":"MovePiece","piece":{"type":"Piece","color":"BLACK","x":1,"y":5},"dx":0,"dy":-1},' +
      '{"type":"MovePiece","piece":{"type":"Piece","color":"BLACK","x":1,"y":5},"dx":0,"dy":-1},' +
      '{"type":"MoveBall","startingPiece":{"type":"Piece","color":"BLACK","x":4,"y":7},' +
      '"endingPiece":{"type":"Piece","color":"BLACK","x":3,"y":7}}],' +
      '"board":{' +
      '"currentWhiteHolder":{"type":"Piece","color":"WHITE","x":4,"y":1},' +
      '"currentBlackHolder":{"type":"Piece","color":"BLACK","x":3,"y":7},"pieces":[' +
      '{"type":"Piece","color":"WHITE","x":1,"y":2},{"type":"Piece","color":"WHITE","x":3,"y":2},' +
      '{"type":"Piece","color":"WHITE","x":3,"y":1},{"type":"Piece","color":"WHITE","x":4,"y":1},' +
      '{"type":"Piece","color":"WHITE","x":5,"y":1},{"type":"Piece","color":"WHITE","x":6,"y":1},' +
      '{"type":"Piece","color":"WHITE","x":7,"y":1},{"type":"Piece","color":"BLACK","x":1,"y":5},' +
      '{"type":"Piece","color":"BLACK","x":2,"y":7},{"type":"Piece","color":"BLACK","x":3,"y":7},' +
      '{"type":"Piece","color":"BLACK","x":4,"y":7},{"type":"Piece","color":"BLACK","x":5,"y":7},' +
      '{"type":"Piece","color":"BLACK","x":6,"y":7},{"type":"Piece","color":"BLACK","x":7,"y":7}]},' +
      '"winner":"NONE"}');
  }
}
