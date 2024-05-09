import useSwitchTheme from '@/utils/theme'
import { useTheme } from 'next-themes'
import { FC } from 'react'
import { BsFillSunFill, BsMoonStarsFill } from 'react-icons/bs'

interface ThemeToggleProps {
  classNames?: string
}

export const ThemeToggle: FC<ThemeToggleProps> = props => {
  const { theme, setTheme } = useTheme()

  return (
    <div>
      <button onClick={useSwitchTheme()} className='btn'>
        {theme === 'dark' ? <BsFillSunFill /> : <BsMoonStarsFill />}
      </button>
    </div>
  )
}
