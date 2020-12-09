import {ClientDoseInfo} from './client-dose-info.model';
import {ClientDoseRaport} from './client-dose-raport.model';

export interface ClientInfoModel {
  info: ClientDoseInfo;
  report: ClientDoseRaport[];
}
