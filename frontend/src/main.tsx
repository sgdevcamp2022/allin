import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { RecoilRoot } from 'recoil'
import './index.css'
import SignInMobilePage from './Pages/Sign/Mobile/SignIn.page'
import { isMobile } from 'react-device-detect'
import SignInDesktopPage from './Pages/Sign/Desktop/SignIn.page'
import SignUpMobilePage from './Pages/Sign/Mobile/SignUp.page'
import SignUpDesktopPage from './Pages/Sign/Desktop/SignUp.page'
import SignUpDoneMobilePage from './Pages/Sign/Mobile/SignUpDone.page'
import SignUpDoneDesktopPage from './Pages/Sign/Desktop/SignUpDone.page'
import HomeMobilePage from './Pages/Home/Mobile/Home.page'
import HomeDesktopPage from './Pages/Home/Desktop/Home.page'

import { worker } from './mocks/worker'

const prepare = async (): Promise<void> => {
  worker.start().then(() => {
    return Promise.resolve()
  })
}

const router = createBrowserRouter([
  {
    path: '/',
    element: isMobile ? <HomeMobilePage /> : <HomeDesktopPage />,
  },
  {
    path: '/sign-in',
    element: isMobile ? <SignInMobilePage /> : <SignInDesktopPage />,
  },
  {
    path: '/sign-up',
    element: isMobile ? <SignUpMobilePage /> : <SignUpDesktopPage />,
  },
  {
    path: '/sign-up-done',
    element: isMobile ? <SignUpDoneMobilePage /> : <SignUpDoneDesktopPage />,
  },
])

prepare().then(() => {
  ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
    <React.StrictMode>
      <RecoilRoot>
        <RouterProvider router={router} />
      </RecoilRoot>
    </React.StrictMode>
  )
})
