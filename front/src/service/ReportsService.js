import axios from "axios";

const URL_API_REPORTS = "http://localhost:8084/report";


class ReportsService {

    getReportsById(patientId) {
        return axios.get(URL_API_REPORTS + "/patient/" + patientId);
    }

   

}

export default new ReportsService();