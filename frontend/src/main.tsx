import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { RecoilRoot } from 'recoil'
import './index.css'

import App from './App'
import SignInMobilePage from './Pages/Mobile/Account/SignIn.page'
import { isMobile } from 'react-device-detect'
import SignInDesktopPage from './Pages/Desktop/Account/SignIn.page'

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
  },
  {
    path: '/sign-in',
    element: isMobile ? <SignInMobilePage /> : <SignInDesktopPage />,
  },
])

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <RecoilRoot>
      <RouterProvider router={router} />
    </RecoilRoot>
  </React.StrictMode>
)
