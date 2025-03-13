import React, { useContext, useState } from 'react';
import {Link, useParams , Outlet, useNavigate} from 'react-router-dom';
import { DataContext } from '../App';
import '../css/boardList-style.css'

const BoardList = () => {
    // 페이지 이동 시
    const navigate = useNavigate();
 

    //useParams() : URL 파라미터에 입력한 데이터를 가지고 옴
    const {boardCode} = useParams();

    const {data,setData} = useContext(DataContext);

    const [modal, setModal] = useState(false);
    /* 선택한 게시글을 기억하기 위한 index */
    const [index,setIndex] = useState(0);

    const likeCount = (i,e) => {
        e.stopPropagation();
        const likeCopy = [...data];
        likeCopy[i].like++;
        setData(likeCopy);
    }

    let count = 0;


    return(
        <>
            <Link to="addBoardList">게시글 추가</Link>
            <Outlet></Outlet>
            {data.map(function(d,i){
                return(
                    <div className='list' key={count++}>
                        <div className='content'>
                            {/* navigate("요청주소") */}
                            <h4 onClick={()=>{navigate(`/${boardCode}/detail/${i}`)}}>{d.title}<span onClick={(e)=>{ //likeCount 함수호출해도됨
                                const newData= [...data]
                                newData[i]={...newData[i], like: newData[i].like+1};
                                console.log(newData);
                                setData(newData);
                            }}>❤️</span>{d.like}</h4>
                            <p>{d.writer} | {d.issueDate}</p>  
                            
                        </div>
                        <div className='board-btn-area'>
                            <button onClick={()=>{
                            setIndex(i)
                            setModal(index=== i ? !modal : true)
                            }}>모달</button>
                        </div>
                    </div>
                );
        
            })}

            {modal && <Modal data={data} index={index}/>}
   
        </>
    );
}

// 모달창 
const Modal = (props) => {
    const data= props.data;
    const index=props.index;
    console.log(props.data)
    return(
     
        <div className='modal'>
            <h4>{data[index].title}</h4>
            <p>{data[index].writer} | {data[index].issueDate}</p>
            <p>{data[index].content}</p>
        </div>
    
    );
}

export default BoardList;