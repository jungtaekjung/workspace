console.log("boardWriteForm.js");

// 미리보기 관련 요소 모두 얻어오기
const inputImage = document.getElementsByClassName('inputImage');
const deleteImage = document.getElementsByClassName('delete-image');
const preview = document.getElementsByClassName('preview');

// 게시글 수정 시 삭제된 이미지의 레벨(위치)을 저장할 input 요소
const deleteList = document.getElementById("deleteList");

const deleteSet = new Set(); // 순서 X, 중복 허용 X -> 여러번 클릭 시 중복 값 저장 방지


for(let i =0; i<inputImage.length; i++){
    // 파일이 선택되었을 때
    inputImage[i].addEventListener("change", e=>{
        
        if(e.target.files[0] != undefined){
            const reader = new FileReader(); // 선택된 파일을 읽을 객체를 생성

            reader.readAsDataURL(e.target.files[0]);
            // 지정된 파일을 읽음 -> result에 저장(URL 포함) -> URL을 이용해서 이미지 보기 가능
            // -> URL을 이용해서 이미지 보기 가능

            reader.onload = function(e){ //reader가 파일을 다 읽어온 경우

                preview[i].setAttribute("src",e.target.result);
                // e.target == reader
                // e.target.result == 읽어들인 이미지의 URL
                // preview[i] == 파일이 선택된 input태그와 인접한 preview 이미지 태그

                // deleteSet에 해당 레벨 제거 -> 삭제 목록에서 제외시킴
                deleteSet.delete(i);

            }
        }else{ // 파일이 선택되지 않았을 때(취소)
            preview[i].removeAttribute("src"); // src 속성 제거
        }
    })
}

// 미리보기 삭제 버튼(X)이 클릭 되었을 때
for(let i=0; i<deleteImage.length; i++){
    deleteImage[i].addEventListener("click", e=>{

        // 미리보기가 존재하는 경우에만 x버튼 동작
        if(preview[i].getAttribute('src') != ''){

            
            // 미리보기 삭제
            preview[i].removeAttribute("src");
            
            // input의 값을 '' 변경
            inputImage[i].value = '';

            //deleteSet에 삭제된 이미지 레벨 추가
            deleteSet.add(i);
            deleteList.value = Array.from(deleteSet);


            
        }
    })
}

// 게시글 작성 유효성 검사
function writeValidate(){

    const boardTitle = document.getElementsByName("boardTitle")[0];
    const boardContent = document.getElementsByName("boardContent")[0];

    if(boardTitle.value.trim()==''){
        alert("제목을 입력해주세요");
        boardTitle.focus();
        return false;
    }

    if(boardContent.value.trim()==''){
        alert("내용을 입력해주세요");
        boardContent.focus();
        return false;
    }

    // 제목, 내용이 유효한 경우
    // deleteList(input 태그 )에 deleteSet(삭제된 이미지 레벨)을 추가
    // -> JS 배열 특징 이용
    //     : JS 배열을 HTML 요소 또는 console에 출력하게 되는 경우
    //       1,2,3 같은 문자열로 출력됨 (배열 기호가 벗겨짐)

    // * Set -> Array로 변경 -> deleteList.value에 대입

    // Array.from(유사배열 | 컬렉션) : 유사 배열 | 컬렉션을 배열로 변환해서 반환



    
    // deleteList.value = deleteSet; // X
}