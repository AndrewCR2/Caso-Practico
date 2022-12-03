(function(){
    const titleQuestions =[...document.querySelectorAll('.questions_title')];

    titleQuestions.forEach(question=>{
        question.addEventListener('click',()=>{
           let height =0;
           let ansewer = question.nextElementSibling;
           let addPadding= question.parentElement.parentElement;

            addPadding.classList.toggle('questions_padding--add');
           question.children[0].classList.toggle('questions_arrow--rotate');

           if(ansewer.clientHeight === 0){
            height = ansewer.scrollHeight;  /*scrollHeigh nos da el alto minimo para que un elemento se muestre*/ 
           }

           ansewer.style.height =  `${height}px`
        })
    });
    
})();