import { useRecoilValue } from 'recoil'
import {
  signUpCheckEmailNumberState,
  signUpCheckPwTextState,
  signUpIdTextState,
  signUpPwTextState,
} from '../../../Atoms/Sign/SignUp.atom'
import InputForm from '../../../Elements/Input/InputForm.element'
import { useEffect, useState } from 'react'
import { isEmail } from '../../../utils/validator'
import HomeButtonElement from '../../../Elements/Button/HomeButton.element'

const SignUpDesktopPage = () => {
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

  return (
    <div className="w-screen h-screen flex flex-col justify-center items-center">
      <HomeButtonElement className="p-16 mt-28" />
      <div className="min-w-[42rem] w-[40%] border-border px-12 py-10 border-[1px] rounded-xl mt-8">
        <InputForm
          textState={signUpIdTextState}
          formTitle="이메일 주소"
          className="flex my-10 justify-between text-xl items-center "
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          placeholder="example@smilegate.com"
          isWarning={!isValidId}
          warningText="이메일 형식이 틀렸습니다."
        />
        <InputForm
          textState={signUpPwTextState}
          formTitle="비밀번호"
          className="flex my-10 justify-between text-xl items-center"
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          type="password"
        />
        <InputForm
          textState={signUpCheckPwTextState}
          formTitle="비밀번호 확인"
          className="flex my-10 justify-between text-xl items-center"
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          type="password"
          isWarning={!isValidPw}
          warningText="비밀번호가 맞지 않습니다."
        />
        <div className="flex w-full items-center my-10">
          <InputForm
            textState={signUpCheckEmailNumberState}
            formTitle="이메일 인증"
            className="flex justify-between text-xl items-center flex-1"
            inputTextClassName="w-[62.5%] text-base p-2 rounded-sm"
            placeholder="인증번호 입력"
            type="number"
          />
          <button className="w-[30%] ml-4 border-border rounded-sm border-[1px] p-2">
            인증번호 발송
          </button>
        </div>
        <button className="bg-intacitve text-white rounded-sm py-2 px-16 mt-4 ml-[25%]">
          가입하기
        </button>
      </div>
    </div>
  )
}

export default SignUpDesktopPage
