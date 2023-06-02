<template>
<div class="stsch_container">
  <div class="stsch_box">
    <div class="stsch">
      <ul class="stsch_form">
        <li>
          <span class="st_dot">
            <label for="stb1">地域区分</label>
          </span>
          <div class="stb_sel">
            <div class="sel5_w">
              <select name="" id="stb1" class="select1">
                <option value="" selected>地域選択</option>
              </select>
            </div>
            <div class="sel5_w">
              <select name="" id="stb1" class="select2">
                <option value="" selected>지역 선택</option>
              </select>
            </div>
            <div class="sel5_w">
              <select name="" id="stb1" class="select3">
                <option value="" selected>지역 선택</option>
              </select>
            </div>
          </div>
        </li>
        <li>
          <span class="st_dot">
            <label for="stb1">地域区分</label>
          </span>
          <div class="stb_sel">
            <div class="sel5_w">
              <input type="text" class="stsch_ipt">
            </div>  
          </div>
        </li>
      </ul>
      <div class="stb_btnwrap">
        <a href="" class="btn sch blue" id="searchButton">
          <span>
            <i class="fa-solid fa-magnifying-glass"></i>
            <!-- 検索 -->
          </span>
        </a>
        <a href="" class="btn sch_all gray">
          <span>詳細</span>
        </a>
      </div>
    </div>
    <div class="stsch_navwrap">
      <div class="stsch_nav_tlt">원하는 서비스를 선택하세요.</div>
      <div class="stsch_nav">
        <ul class="nav_top"></ul>
        <ul></ul>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
  @import url('https://netdna.bootstrapcdn.com/font-awesome/2.0/css/font-awesome.css');
  .stsch_box {
    border: 1px solid #cccccc;
    padding: 20px 57px 20px 60px;
  }
  .stsch_form li {
    display: flex;
    flex-direction: row;
    /* background-color: ; */
  }
  .stb_sel {
    display: flex;
    flex-direction: row;
  }
  .stb_sel select {
    margin-left: 1.3vw;
    /* width: 100%; */
    width: 15vw;
    border: 1px solid #dddddd;
    color: #999999;
    font-size: 14px;
    padding: 10px;
  }
  option {
    padding: 10px;
    font-weight: normal;
    display: block;
    white-space: nowrap;
    min-height: 1.2em;
    padding: 0px 2px 1px;
  }

  .stsch_form li {
    border-bottom: 1px dotted #cccccc;
    padding: 2vw 0vh 3vh 2vw;
    margin-bottom: 2vh
}
.stsch_form span {
    display: inline-block;
    vertical-align: top;
    width: 140px;
    /* background: url(/_ui/desktop/common/images/gscvs/store/icon_store.png) no-repeat; */
    color: #444;
    font-size: 16px;
    padding: 8px 0px 0px 12px;
}
.stb_btnwrap .sch {
    padding: 12px 0;
    width: 94px;
    /* background: url(/_ui/desktop/common/images/gscvs/store/icon_store.png) no-repeat #397ca8; */
    /* color: #fff; */
    background-color: #ffdf65;
    color: white;
    width: 100px;
    height: 78px;
    text-align: center;
}
.stb_btnwrap .sch {
    padding: 12px 0;
    width: 94px;
    /* background: url(/_ui/desktop/common/images/gscvs/store/icon_store.png) no-repeat #397CB6; */
    /* color: #fff; */
    width: 100px;
    height: 78px;
    text-align: center;
}
.stsch_navwrap {
    clear: both;
    border-top: 1px dotted #cccccc;
}
.stsch_navwrap .stsch_nav_tlt {
    vertical-align: top;
    /* background: url(/_ui/desktop/common/images/gscvs/store/icon_store.png) no-repeat; */
    color: #444;
    font-size: 16px;
    padding: 8px 0px 0px 20px;
    background-position: 10px 14px;
    margin: 12px 0px 10px;
}
.stsch_form .stsch_ipt {
    width: 418px;
    height: 34px;
    border: 2px solid #666;
}
input[type=text], input[type=password] {
    text-indent: 5px;
    border: 1px solid #d6d6d6;
    height: 29px;
    line-height: 31px;
    color: #666;
    font-size: 13px;
}

.stsch_container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.fa-magnifying-glass {
  font-size: 5vmin;
}
</style>