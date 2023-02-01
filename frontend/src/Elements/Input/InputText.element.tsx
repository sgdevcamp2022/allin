import { ChangeEvent } from 'react'
import { RecoilState, useRecoilState } from 'recoil'

const InputTextElement = ({
  textState,
  className = '',
  placeholder = '',
}: {
  textState: RecoilState<string>
  className?: string
  placeholder?: string
}) => {
  const [text, setText] = useRecoilState(textState)

  const onChange = (event: ChangeEvent<HTMLInputElement>) => {
    setText(event.target.value)
  }

  return (
    <input
      className={className + ' focus:outline-none'}
      type="text"
      value={text}
      onChange={onChange}
      placeholder={placeholder}
    />
  )
}

const InpuSecretTextElement = ({
  textState,
  className = '',
  placeholder = '',
}: {
  textState: RecoilState<string>
  className?: string
  placeholder?: string
}) => {
  const [text, setText] = useRecoilState(textState)

  const onChange = (event: ChangeEvent<HTMLInputElement>) => {
    setText(event.target.value)
  }

  return (
    <input
      className={className + ' focus:outline-none'}
      type="password"
      value={text}
      onChange={onChange}
      placeholder={placeholder}
    />
  )
}

export { InputTextElement, InpuSecretTextElement }
