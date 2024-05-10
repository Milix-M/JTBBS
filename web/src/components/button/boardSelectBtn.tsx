import Link from "next/link";
import { FC } from "react";

interface BoardSelectBtnProps {
  boardName: string;
  url: string;
}

const BoardSelectBtn: FC<BoardSelectBtnProps> = (props) => {
  return (
    <div>
      <Link href={props.url}>
        <button className="btn btn-neutral">{props.boardName}</button>
      </Link>
    </div>
  );
};

export default BoardSelectBtn;
