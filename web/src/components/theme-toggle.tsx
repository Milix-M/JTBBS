import useSwitchTheme from '@/utils/theme'
import { useTheme } from 'next-themes'
import { FC, useEffect, useState } from 'react'
import { BsFillSunFill, BsMoonStarsFill } from 'react-icons/bs'

interface ThemeToggleProps {
  classNames?: string
}

export const ThemeToggle: FC<ThemeToggleProps> = props => {
  const [mounted, setMounted] = useState(false)
  const { theme, setTheme } = useTheme()
  const themeSwitcher = useSwitchTheme()

  useEffect(() => {
    setMounted(true)
  }, [])

  return (
    <div>
      {mounted && (
        <button onClick={themeSwitcher} className='btn'>
          {theme === 'dark' ? <BsFillSunFill /> : <BsMoonStarsFill />}
        </button>
      )}
    </div>
  )
}
