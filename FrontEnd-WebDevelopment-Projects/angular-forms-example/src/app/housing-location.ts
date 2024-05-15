export class HousingLocation {
    id: number;
    name: string;
    city: string;
    state: string;
    photo: string;
    availableUnits: number;
    wifi: boolean;
    laundry: boolean;
  
    constructor() {
      // Assign default values
      this.id = 0;
      this.name = '';
      this.city = '';
      this.state = '';
      this.photo = '';
      this.availableUnits = 0;
      this.wifi = true;
      this.laundry = true;
    }
  }
  