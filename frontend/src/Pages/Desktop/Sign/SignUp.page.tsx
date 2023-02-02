import {
  signUpCheckEmailNumberState,
  signUpCheckPwTextState,
  signUpIdTextState,
  signUpPwTextState,
} from '../../../Atoms/Sign/SignUp.atom'
import InputForm from '../../../Elements/Input/InputForm.element'

const SignUpDesktopPage = () => {
  return (
    <div className="w-screen h-screen flex flex-col justify-center items-center">
      <img src="/src/assets/logo.svg" alt="" />
      <div className="min-w-[42rem] w-[40%] border-border px-12 py-10 border-[1px] rounded-xl mt-8">
        <InputForm
          textState={signUpIdTextState}
          formTitle="이메일 주소"
          className="flex my-4 justify-between text-xl items-center"
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          placeholder="example@smilegate.com"
        />
        <InputForm
          textState={signUpPwTextState}
          formTitle="비밀번호"
          className="flex my-4 justify-between text-xl items-center"
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          type="password"
        />
        <InputForm
          textState={signUpCheckPwTextState}
          formTitle="비밀번호 확인"
          className="flex my-4 justify-between text-xl items-center"
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          type="password"
        />
        <div className="flex w-full items-center my-4">
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
