import React, {useContext, useState} from 'react';
import {Link, useParams} from 'react-router-dom';
import { DataContext } from '../App';
import'../css/boardList-style.css';

const BoardList = () =>{

   


    // useParams() : URL 파라미터에 입력한 데이터를 가지고 옴
    const {boardCode} = useParams();

    const {data, setData} = useContext(DataContext);

    const [modal, setModal] =useState(false);

    
    const [index, setIndex] = useState(0);

    const likeCount = (i) =>{
        const Countdata = [...data];
        Countdata[i].like += 1;
        setData(Countdata);
    }


    let count = 0;

    const handleModal = (i) => {
        setIndex(i); 
        setModal(index == i ? !modal : true); 
    };




    return(
       <>
        {data.map(function(d,i){
            return(
                <div className='list' key={count++}>
                    <div className='content'>
                        <h4>{d.title}<button onClick={()=> likeCount(i)}><span>❤️</span></button>{d.like}</h4>
                        <p>{d.writer} | {d.issueDate}</p>
                    </div>

                    <div className='board-btn-area'>
                        <button onClick={() => handleModal(i)}>모달</button>
                    </div>

                </div>
            )
        })}
        {modal && <Modal data={data} index={index}/>}
        
       </>

    );
}

// 모달창
const Modal = (props) => {
    const data = props.data;
    const index = props.index;
    return(
        <div className='modal'>
            <h4>{data[index].title}</h4>
            <p>{data[index].writer} | {data[index].issueDate}</p>
            <p>{data[index].content}</p>
        </div>

    );

}

export default BoardList;