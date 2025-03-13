import React, { useContext, useState } from 'react';
import { DataContext } from '../App';

const AddBoardList = () =>{
    const {data, setData} = useContext(DataContext);
    const [input, setInput] = useState('');


   const addList = () => {
    if(input.trim() === ''){
        alert("내용을 입력해주세요.");
        return;
    }

    // 새로운 데이터 추가
    const dataCopy = [ {title : "강남 놀거리 추천!", writer : '유저일', like : 0, issueDate : '2025-03-13', updateDate : '', content : '' }, ...data]
    setData(dataCopy);
    setInput('');
    }
    
    return(
        <>
            <div className='header'>
                <input value={input} onChange={e => {setInput(e.target.value)}}/>
                {/* 새로운 글 추가 */}
                <button onClick={addList}>추가</button>
                
                {/* 제목을 가나다 순으로 정렬 */}
                <button onClick={()=>{
                    let copy = [...data];
                    copy.sort((a,b)=>{
                        if(a.title > b.title) return 1;
                        if(a.title < b.title) return -1;
                        return 0;
                    })
                    setData(copy)
                }}>제목 정렬</button>
            </div>
        </>
    );
}

export default AddBoardList;