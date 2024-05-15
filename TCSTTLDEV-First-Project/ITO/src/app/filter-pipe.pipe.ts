import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'trimPipe'
})
export class FilterPipePipe implements PipeTransform {

  transform(searchText: string): any {
    console.log(searchText.trim());
    return searchText.trim();
  }

}
