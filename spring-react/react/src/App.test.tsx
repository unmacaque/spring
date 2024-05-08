import { render, screen } from '@testing-library/react'
import { act } from 'react'
import { expect, test, vi } from 'vitest'
import App from './App'

function mockResponse(data: unknown) {
  return { ok: true, json: () => new Promise((resolve) => resolve(data)) }
}

test('renders spring-react title', async () => {
  window.fetch = vi.fn().mockReturnValueOnce(mockResponse([]))

  await act(async () => {
    render(<App />)
  })

  const linkElement = screen.getByText(/Spring React/i)

  expect(linkElement).toBeDefined()
  expect(window.fetch).toHaveBeenCalledOnce()
})
