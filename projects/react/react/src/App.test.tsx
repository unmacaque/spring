import { render, screen } from '@testing-library/react'
import { expect, test } from 'vitest'
import App from './App'

test('renders App', async () => {
  render(<App />)

  const linkElement = screen.getByText(/Login required/i)

  expect(linkElement).toBeDefined()
})
