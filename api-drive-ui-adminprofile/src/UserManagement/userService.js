import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/userprofiles';

export const uploadExcelSheet = (file) => {
  const formData = new FormData();
  formData.append('file', file);

  return axios.post(`${BASE_URL}/upload`, formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
};

export const submitUserData = (userData) => {
  return axios.post(BASE_URL, userData, {
    headers: {
      'Content-Type': 'application/json'
    }
  });
};
