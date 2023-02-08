import axios from "axios";

const URL_API_NOTES = "http://localhost:8082/note";

class NotesService {

    getNotes() {
        return axios.get('http://localhost:8082/notes');
    }

    getNotesById(patientId) {
        return axios.get(URL_API_NOTES + '/patient/' + patientId);
    }
    getNoteById(noteId) {
        return axios.get(URL_API_NOTES + '/' + noteId);
    }
    createNotes(note) {
        return axios.post(URL_API_NOTES+'/add', note);
    }

    updateNotes(note, notesId) {
        return axios.put(URL_API_NOTES + '/update/' + notesId, note);
    }

   
    deleteNotes(notesId) {
        return axios.delete(URL_API_NOTES + '/delete/' + notesId);
    }
}

export default new NotesService();