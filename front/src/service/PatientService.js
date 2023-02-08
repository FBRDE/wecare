import axios from "axios";

const URL_API_PATIENT = "http://localhost:8081/patient";
const URL_API_PATIENTS = "http://localhost:8081/patients";
class PatientService {
    
    getPatient() {
        return axios.get(URL_API_PATIENTS);
    }

    getPatientById(patientId) {
        return axios.get(URL_API_PATIENT + '/' + patientId);
    }

    createPatient(patient) {
        return axios.post(URL_API_PATIENT+'/add', patient);
    }

    updatePatient(patient, patientId) {
        return axios.put(URL_API_PATIENT + '/update/' + patientId, patient);
    }

    deletePatient(patientId) {
        return axios.delete(URL_API_PATIENT + '/delete/' + patientId);
    }
}

export default new PatientService();