import { ChangeEvent, useState } from 'react'
import { signInIdTextState, signInPwTextState } from '../../../Atoms/Sign/SignIn.atom'
import { useRecoilValue } from 'recoil'
import InputForm from '../../../Elements/Input/InputForm.element'

const SignInMobilePage = () => {
  const singInId = useRecoilValue(signInIdTextState)
  const singInPw = useRecoilValue(signInPwTextState)

  const [isMaintenanceLogin, setIsMaintenanceLogin] = useState(false)
  const [isWorngLogin, setIsWorngLogin] = useState(false)
  const changeMaintenanceLogin = (e: ChangeEvent<HTMLInputElement>) => {
    setIsMaintenanceLogin(e.target.checked)
  }

  const goFindPassword = () => {
    location.href = '/find-password'
  }

  const goSignUp = () => {
    location.href = '/sign-up'
  }

  const goLogin = () => {
    setIsWorngLogin(true)
  }

  return (
    <div className="w-screen h-screen flex flex-col justify-between items-center px-[10%]">
      <div className="w-full flex flex-col items-center">
        <button
          onClick={() => {
            location.href = '/'
          }}
          className="p-16 mt-28"
        >
          <img src="/src/assets/logo.svg" alt="" />
        </button>
        <div className="w-full">
          <InputForm
            textState={signInIdTextState}
            className="w-full py-[5%] font-light text-base text-mainText"
            inputTextClassName="text-sm p-2 rounded-sm"
            formTitle="아이디"
          />
          <InputForm
            textState={signInPwTextState}
            className="w-full py-[5%] font-light text-base text-mainText"
            inputTextClassName="text-sm p-2 rounded-sm"
            formTitle="비밀번호"
            type="password"
          />
          <div className="flex justify-between w-full text-sm items-center flex-1">
            <button className="text-warningText" onClick={goFindPassword}>
              비밀번호를 잊으셨나요?
            </button>
            <div className="flex items-center ">
              <input
                type="checkbox"
                name="maintenanceLogin"
                id="maintenanceLogin"
                className="accent-point"
                checked={isMaintenanceLogin}
                onChange={changeMaintenanceLogin}
              />
              <label htmlFor="maintenanceLogin" className="pl-2 text-subText">
                로그인 상태 유지
              </label>
            </div>
          </div>
          <div className="text-warningText w-full text-center mt-6 h-6 text-sm">
            {isWorngLogin ? '아이디 또는 비밀번호가 틀렸습니다.' : ''}
          </div>
          <button className="bg-point text-mainTextWhite rounded-sm w-full p-2 mt-4">로그인</button>
        </div>
      </div>
      <button className="mb-8 text-subText text-sm" onClick={goSignUp}>
        이미 회원가입 하지 않으셨나요?
        <span className="text-point font-semibold ml-2">회원가입</span>
      </button>
    </div>
  )
}

export default SignInMobilePage
