 /**
 * Created by Goilo on 30.08.14.
 */
function getXmlHttp(){
    var xmlhttp;
    try {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (E) {
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
        xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
}
function jsBitloorOK(req,s,succ)
{
    var AjaxData;
    if(req.status == 200) {
        // если статус 200 (ОК) - выдать ответ пользователю
        if('teg' in s)
        {
            var ArrayTegs = s.teg.split(',');
            AjaxData = {};
            for(var i= 0; i<ArrayTegs.length;i++)
            {
                if(ArrayTegs[i].substr(0,1)=='#')
                {
                    var el = document.createElement( 'div' );
                    // Делаем его невидимым
                    el.style.display = 'none';
                    // Заполняем его полученным кодом
                    el.innerHTML = req.responseText;
// Добавляем его в документ
                    document.body.appendChild(el);
// т.к он уже на странице (но его не видно), мы можем к нему обращаться стандартными способами
                    AjaxData[ArrayTegs[i].substr(1)] = document.getElementById(ArrayTegs[i].substr(1)).outerHTML;
// Незабываем удалять созданный элемент

                    document.body.removeChild(el);
                }
                else
                {
                    for(var a=0; a<req.responseXML.getElementsByTagName(ArrayTegs[i]).length;a++)
                    {
                        if(a==0)
                        {
                            AjaxData[ArrayTegs[i]] = req.responseXML.getElementsByTagName(ArrayTegs[i])[a].innerHTML;
                        }
                        else
                        {
                            AjaxData[ArrayTegs[i]] = AjaxData[ArrayTegs[i]] + req.responseXML.getElementsByTagName(ArrayTegs[i])[a].innerHTML;
                        }
                    }
                }
            }
        }
        else
        {
            AjaxData = req.responseText;
        }
        succ(AjaxData);
    }
}
function jsITBL(s)
{
    if('type' in s){}else
    {
        s['type'] = 'GET';
    }
}
function jBL (name) {
    return {
        onIdObj:function()
        {
            return  document.getElementById(name);
        },
        click:function(d)
        {
            jBL(name).onIdObj().onclick = function()
            {d();}
        },
            goAjax:function(s,p,strt,succ) {
            // (1) создать объект для запроса к серверу
            var req = getXmlHttp();

            // (2)

            req.onload = function() {
                // onreadystatechange активируется при получении ответа сервера
                // если запрос закончил выполняться
                jsBitloorOK(req,s,succ);
                // тут можно добавить else с обработкой ошибок запроса
            };
            // (3) задать адрес подключения
            jsITBL(s);
            req.open(s.type,s.url, true);

            // объект запроса подготовлен: указан адрес и создана функция onreadystatechange
            // для обработки ответа сервера

            // (4)
            req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            var ParamsGO = '';
            for(var k in p)
            {
                ParamsGO = ParamsGO + "&" + k + "=" + p[k];
            }
            req.send(ParamsGO);// отослать запрос

            // (5)
            strt();
        },
        goFiles:function(s,p,strt,ld,succ,e)
        {
            var req = new XMLHttpRequest();
            var form = new FormData();
            var FileGo = document.getElementById(name).files;

            if('max' in s){

            }else
            {
                s['max'] = 1;
            }
            if(FileGo.length> s.max)
            {
                e(1+'*'+FileGo.length);//превышел лимит файлов
            }
            else
            {
                if('formats' in s){
                    var arrayFormats = s.formats.split(',');
                }
                else
                {
                    s['formats'] = 'none';
                }
                var erF = '';
                if(s.formats!='none')
                {
                    for(var a= 0; FileGo.length>a;a++)
                    {
                        var yr = 0;
                        var filetype = FileGo[a].name.split('.').pop();
                        for(var n=0; arrayFormats.length>n ; n++)
                        {
                            if(filetype==arrayFormats[n])
                            {
                                yr=1;
                                break;
                            }
                        }
                        if(yr==0)
                        {
                            erF = erF +"*"+FileGo[a].name;
                        }
                    }
                    if(erF!="")
                    {
                        e(2+erF);//неверное расширение
                    }
                }
                if('size' in s){
                }
                else
                {
                    s['size'] = 'none';
                }
                if(erF=='' && s.size!='none')
                {
                    erF="";
                    for(a= 0; FileGo.length>a; a++)
                    {
                        if(FileGo[a].size > s.size)
                        {
                            erF = erF+'*'+FileGo[a].name;
                        }
                    }
                    if(erF!='')
                    {
                        e(3+erF);//превышен размер
                    }
                }
                if(erF=="")
                {
                    var v= 0;
                    var f= 0;
                    function fileStrt()
                    {
                        if(v==0)
                        {
                            for(var k in p)
                            {
                                form.append(k,p[k]);
                            }
                            for(var x = 0; x < FileGo.length;x++)
                            {
                                form.append(s.file+x,FileGo[x]);
                                alert(FileGo[x].name);
                            }
                            jsITBL(s);
                            req.open(s.type, s.url,true);
                            req.onload = function(){
                                jsBitloorOK(req,s,succ);
                            };
                            req.send(form);
                            strt();
                            /*var upload_file= FileGo[f];
                            for(var k in p)
                            {
                                form.append(k,p[k]);
                            }
                            form.append(s.file,upload_file);
                            jsITBL(s);
                            req.open(s.type, s.url,true);
                            req.onload = function(){
                                jsBitloorOK(req,s,succ);
                                if(FileGo.length-1>f)
                                {
                                    f++;
                                }
                                else
                                {
                                    clearInterval(IntFile);
                                }
                                v=0;
                            };
                            req.upload.onprogress =function (ev) {
                                var progress = ev.loaded / ev.total * 100;
                                ld(progress);
                            };
                            req.send(form);
                            strt();
                            v=1;*/
                        }
                    }
                    fileStrt();
                   // var IntFile = setInterval(fileStrt,1000);

                }
            }
        }
    };
}
