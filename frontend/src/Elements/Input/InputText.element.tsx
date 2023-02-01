import { ChangeEvent } from 'react'
import { RecoilState, useRecoilState } from 'recoil'

const InputTextElement = ({
  textState,
  placeholder,
}: {
  textState: RecoilState<string>
  placeholder: string | undefined
}) => {
  const [text, setText] = useRecoilState(textState)

  const onChange = (event: ChangeEvent<HTMLInputElement>) => {
    setText(event.target.value)
  }

  return (
    <div>
      <input
        className="w-full"
        type="text"
        value={text}
        onChange={onChange}
        placeholder={placeholder ?? ''}
      />
    </div>
  )
}

export default InputTextElement
