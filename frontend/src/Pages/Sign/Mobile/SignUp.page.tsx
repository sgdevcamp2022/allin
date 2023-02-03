import { useRecoilValue } from 'recoil'
import {
  signUpCheckEmailNumberState,
  signUpCheckPwTextState,
  signUpIdTextState,
  signUpPwTextState,
} from '../../../Atoms/Sign/SignUp.atom'
import { ChangeEvent, useEffect, useState } from 'react'
import InputForm from '../../../Elements/Input/InputForm.element'
import { isEmail } from '../../../utils/validator'

const SignUpMobilePage = () => {
  const signUpId = useRecoilValue(signUpIdTextState)
  const signUpPw = useRecoilValue(signUpPwTextState)
  const signUpCheckPw = useRecoilValue(signUpCheckPwTextState)
  const [isValidId, setIsValidId] = useState(true)
  const [isValidPw, setisValidPw] = useState(true)

  useEffect(() => {
    setIsValidId(signUpId == '' || isEmail(signUpId))
  }, [signUpId])

  useEffect(() => {
    setisValidPw(signUpPw == signUpCheckPw)
  }, [signUpPw, signUpCheckPw])
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
          className="p-16 mt-12"
        >
          <img src="/src/assets/logo.svg" alt="" />
        </button>
        <div className="w-full">
          <InputForm
            formTitle="이메일 주소"
            textState={signUpIdTextState}
            className="w-full py-[5%] font-light text-base text-mainText"
            inputTextClassName="text-sm p-2 rounded-sm"
            warningClassName="font-light text-xs !translate-y-[-130%]"
            placeholder="example@smilegate.com"
            isWarning={!isValidId}
            warningText="이메일 형식이 틀렸습니다."
          />
          <InputForm
            textState={signUpPwTextState}
            formTitle="비밀번호"
            className="w-full py-[5%] font-light text-base text-mainText"
            inputTextClassName="text-sm p-2 rounded-sm"
            type="password"
          />
          <InputForm
            textState={signUpCheckPwTextState}
            formTitle="비밀번호 확인"
            className="w-full py-[5%] font-light text-base text-mainText"
            warningClassName="font-light text-xs !translate-y-[-130%]"
            inputTextClassName="text-sm p-2 rounded-sm"
            type="password"
            isWarning={!isValidPw}
            warningText="비밀번호가 맞지 않습니다."
          />
          <div className="flex py-[5%] items-end">
            <InputForm
              textState={signUpCheckEmailNumberState}
              formTitle="이메일 인증"
              className="w-full font-light text-base text-mainText"
              inputTextClassName="w-full text-base p-2 rounded-sm"
              placeholder="인증번호 입력"
              type="number"
            />
            <button className="h-[50%] w-full ml-4 border-border rounded-sm border-[1px] p-2">
              인증번호 발송
            </button>
          </div>
          <button className="bg-intacitve text-white rounded-sm w-full p-2 mt-12">가입하기</button>
        </div>
      </div>
    </div>
  )
}

export default SignUpMobilePage
