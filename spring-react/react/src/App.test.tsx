import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

test('renders spring-react title', () => {
  const { getByText } = render(<App />);
  const linkElement = getByText(/spring-react/i);
  expect(linkElement).toBeInTheDocument();
});
