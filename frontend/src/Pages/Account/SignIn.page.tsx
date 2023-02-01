import { signInTextState } from '../../Atoms/Sign/SingIn.atom'
import InputTextElement from '../../Elements/Input/InputText.element'

const SignInPage = () => {
  return (
    <div className="w-screen">
      <InputTextElement className="bg-point w-[100px]" textState={signInTextState} />
    </div>
  )
}

export default SignInPage
