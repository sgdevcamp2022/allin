import { useEffect } from 'react'
import { signInIdTextState, signInPwTextState } from '../../Atoms/Sign/SingIn.atom'
import { InputSecretTextForm, InputTextForm } from '../../Elements/Input/InputTextForm.element'
import { useRecoilValue } from 'recoil'

const SignInPage = () => {
  const singInId = useRecoilValue(signInIdTextState)
  const singInPw = useRecoilValue(signInPwTextState)

  return (
    <div className="w-screen h-screen flex flex-col justify-center items-center">
      <div>
        <img src="/src/assets/logo.svg" alt="" />
      </div>
      <div>
        <InputTextForm
          textState={signInIdTextState}
          className="w-screen px-[10%] py-[5%]"
          inputTextClassName="text-[14px] p-2 rounded-sm"
          formTitle="아이디"
        />
        <InputSecretTextForm
          textState={signInPwTextState}
          className="w-screen px-[10%] py-[5%]"
          inputTextClassName="text-[14px] p-2 rounded-sm"
          formTitle="비밀번호"
        />
      </div>
    </div>
  )
}

export default SignInPage
