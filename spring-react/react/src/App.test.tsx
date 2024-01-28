import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App';

test('renders spring-react title', () => {
  const { getByText } = render(<App />);
  const linkElement = screen.getByText(/spring-react/i);
  expect(linkElement).toBeDefined();
});
