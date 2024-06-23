import React from 'react'
import ReactDOM from 'react-dom/client'
import { AuthProvider, TAuthConfig } from 'react-oauth2-code-pkce'
import App from './App.tsx'

const authConfig: TAuthConfig = {
  clientId: 'react-client',
  authorizationEndpoint: 'http://localhost:8080/oauth2/authorize',
  tokenEndpoint: 'http://localhost:8080/oauth2/token',
  redirectUri: window.location.origin,
  scope: 'openid profile',
  autoLogin: false,
}

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <AuthProvider authConfig={authConfig}>
      <App />
    </AuthProvider>
  </React.StrictMode>
)
