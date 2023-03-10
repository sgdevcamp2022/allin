import { useRecoilValue } from 'recoil'
import {
  signUpNickNameTextState,
  signUpUserNameTextState,
  signUpCheckPwTextState,
  signUpIdTextState,
  signUpPwTextState,
} from '../../../Atoms/Sign/SignUp.atom'
import InputForm from '../../../Elements/Input/InputForm.element'
import { useEffect, useState } from 'react'
import { isEmail } from '../../../utils/validator'
import HomeButtonElement from '../../../Elements/Button/HomeButton.element'
import { signUpAxios } from '../sign.axios'

const SignUpDesktopPage = () => {
  const signUpId = useRecoilValue(signUpIdTextState)
  const signUpPw = useRecoilValue(signUpPwTextState)
  const signUpCheckPw = useRecoilValue(signUpCheckPwTextState)
  const signUpNickName = useRecoilValue(signUpNickNameTextState)
  const signUpUserName = useRecoilValue(signUpUserNameTextState)
  const [isValidId, setIsValidId] = useState(true)
  const [isValidPw, setisValidPw] = useState(true)
  const [signUpButtonColor, setSignUpButtonColor] = useState('bg-intacitve')
  const canValidSignUp = () =>
    isValidId && isValidPw && signUpId && signUpPw && signUpNickName && signUpUserName
  useEffect(() => {
    localStorage.clear()
    sessionStorage.clear()
  })

  useEffect(() => {
    setIsValidId(signUpId == '' || isEmail(signUpId))
  }, [signUpId])

  useEffect(() => {
    setisValidPw(signUpPw == signUpCheckPw)
  }, [signUpPw, signUpCheckPw])

  useEffect(() => {
    if (canValidSignUp()) {
      setSignUpButtonColor('bg-point')
    } else {
      setSignUpButtonColor('bg-intacitve')
    }
  }, [isValidId, isValidPw, signUpId, signUpPw, signUpUserName, signUpNickName])

  const goSignUp = () => {
    if (canValidSignUp()) {
      signUpAxios(signUpId, signUpPw, signUpNickName, signUpUserName)
    }
  }

  return (
    <div className="w-screen h-screen flex flex-col justify-center items-center">
      <HomeButtonElement />
      <div className="min-w-[42rem] w-[40%] border-border px-12 py-10 border-[1px] rounded-xl mt-8">
        <InputForm
          textState={signUpIdTextState}
          formTitle="????????? ??????"
          className="flex my-10 justify-between text-xl items-center "
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          placeholder="example@smilegate.com"
          isWarning={!isValidId}
          warningText="????????? ????????? ???????????????."
        />
        <InputForm
          textState={signUpPwTextState}
          formTitle="????????????"
          className="flex my-10 justify-between text-xl items-center"
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          type="password"
        />
        <InputForm
          textState={signUpCheckPwTextState}
          formTitle="???????????? ??????"
          className="flex my-10 justify-between text-xl items-center"
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          type="password"
          isWarning={!isValidPw}
          warningText="??????????????? ?????? ????????????."
        />
        <InputForm
          textState={signUpNickNameTextState}
          formTitle="?????????"
          className="flex my-10 justify-between text-xl items-center "
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          placeholder="loopy"
        />
        <InputForm
          textState={signUpUserNameTextState}
          formTitle="??????"
          className="flex my-10 justify-between text-xl items-center "
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          placeholder="?????????"
        />
        {/* <div className="flex w-full items-center my-10">
          <InputForm
            textState={signUpCheckEmailNumberState}
            formTitle="????????? ??????"
            className="flex justify-between text-xl items-center flex-1"
            inputTextClassName="w-[62.5%] text-base p-2 rounded-sm"
            placeholder="???????????? ??????"
            type="number"
          />
          <button className="w-[30%] ml-4 border-border rounded-sm border-[1px] p-2">
            ???????????? ??????
          </button>
        </div> */}
        <button
          className={'text-white rounded-sm py-2 px-16 mt-4 ml-[25%] ' + signUpButtonColor}
          onClick={goSignUp}
        >
          ????????????
        </button>
      </div>
    </div>
  )
}

export default SignUpDesktopPage
