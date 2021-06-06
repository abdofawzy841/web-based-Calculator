import axios from 'axios'

class service {

    
   
    retrieveequal(id) {
        
        return axios.get(`http://localhost:8020/equal?mystring=${id}`);
    }
    retrievepercent(id) {
        
        return axios.get(`http://localhost:8020/percent?mystring=${id}`);
    }
    retrievefrac(id) {
        
        return axios.get(`http://localhost:8020/frac?mystring=${id}`);
    }
    retrievesquare(id) {
        
        return axios.get(`http://localhost:8020/square?mystring=${id}`);
    }
    retrieveroot(id) {
        
        return axios.get(`http://localhost:8020/root?mystring=${id}`);
    }

    
}

export default new service()