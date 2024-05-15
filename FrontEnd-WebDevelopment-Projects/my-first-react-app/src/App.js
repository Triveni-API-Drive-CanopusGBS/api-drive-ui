import React, { useState } from 'react';

function InputForm() {
  const [name, setName] = useState('');
  const [id, setId] = useState('');

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleIdChange = (event) => {
    setId(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Do something with the name and id, like sending it to a server or processing it
    console.log('Name:', name);
    console.log('ID:', id);
    // Clear the inputs
    setName('');
    setId('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Name:
        <input type="text" value={name} onChange={handleNameChange} />
      </label>
      <label>
        ID:
        <input type="text" value={id} onChange={handleIdChange} />
      </label>
      <button type="submit">Submit</button>
    </form>
  );
}

export default InputForm;
