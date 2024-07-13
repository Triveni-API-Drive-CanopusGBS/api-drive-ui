

import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/userprofiles';
const credentials = btoa('root:root');

export const uploadExcelSheet = (file) => {
  const formData = new FormData();
  formData.append('file', file);

  return axios.post(`${BASE_URL}/upload`, formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
      'Authorization': `Basic ${credentials}`
    }
  });
};

export const submitUserData = (userData) => {
  return axios.post(BASE_URL, userData, {
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Basic ${credentials}`
    }
  });
};


export const fetchRoles = async () => {
  const response = await fetch(`${BASE_URL}/rolenames`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Basic ${credentials}`
    }
  });
  if (!response.ok) {
    throw new Error('Failed to fetch roles');
  }
  return response.json();
};

export const fetchDepartments = async () => {
  const response = await fetch(`${BASE_URL}/departmentnames`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Basic ${credentials}`
    }
  });
  if (!response.ok) {
    throw new Error('Failed to fetch departments');
  }
  return response.json();
};

export const fetchRegions = async () => {
  const response = await fetch(`${BASE_URL}/regionnames`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Basic ${credentials}`
    }
  });
  if (!response.ok) {
    throw new Error('Failed to fetch regions');
  }
  return response.json();
};

export const fetchProfile = async (emailId, userId) => {
    const queryParams = new URLSearchParams({ emailId, userId }).toString();

    try {
        const response = await fetch(`${BASE_URL}/profile?${queryParams}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // Include Authorization header if needed
                // 'Authorization': `Basic ${credentials}`
            }
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`Network response was not ok: ${response.status} - ${errorText}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching profile data:', error.message);
        throw error;
    }
};

