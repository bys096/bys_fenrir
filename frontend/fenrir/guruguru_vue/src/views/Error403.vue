<template>
  <div class="error">
    <div class="container-floud">
      <div class="col-xs-12 ground-color text-center">
        <div class="container-error-404">
          <div class="clip">
            <div class="shadow">
              <span class="digit thirdDigit">{{ thirdDigit }}</span>
            </div>
          </div>
          <div class="clip">
            <div class="shadow">
              <span class="digit secondDigit">{{ secondDigit }}</span>
            </div>
          </div>
          <div class="clip">
            <div class="shadow">
              <span class="digit firstDigit">{{ firstDigit }}</span>
            </div>
          </div>
          <div class="msg">OH!<span class="triangle"></span></div>
        </div>
        <h2 class="h1">Sorry! Access Forbidden</h2>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      thirdDigit: null,
      secondDigit: null,
      firstDigit: null,
    };
  },
  methods: {
    randomNum() {
      return Math.floor(Math.random() * 9) + 1;
    },
  },
  created() {
    let time = 30;
    let i = 0;
    let loop1, loop2, loop3;

    loop3 = setInterval(() => {
      if (i > 40) {
        clearInterval(loop3);
        this.thirdDigit = 4;
      } else {
        this.thirdDigit = this.randomNum();
        i++;
      }
    }, time);

    loop2 = setInterval(() => {
      if (i > 80) {
        clearInterval(loop2);
        this.secondDigit = 0;
      } else {
        this.secondDigit = this.randomNum();
        i++;
      }
    }, time);

    loop1 = setInterval(() => {
      if (i > 100) {
        clearInterval(loop1);
        this.firstDigit = 3;
      } else {
        this.firstDigit = this.randomNum();
        i++;
      }
    }, time);
  },
};
</script>

<style scoped>
.error .clip .shadow {
  height: 180px; /* Control */
}
.error .clip:nth-of-type(2) .shadow {
  width: 130px; /* Control play with JavaScript */
}
.error .clip:nth-of-type(1) .shadow,
.error .clip:nth-of-type(3) .shadow {
  width: 250px; /* Control */
}
.error .digit {
  width: 150px; /* Control */
  height: 150px; /* Control */
  line-height: 150px; /* Control */
  font-size: 120px;
  font-weight: bold;
}
.error h2 {
  font-size: 32px; /* Control */
}
.error .msg {
  top: -190px;
  left: 30%;
  width: 80px;
  height: 80px;
  line-height: 80px;
  font-size: 32px;
}
.error span.triangle {
  top: 70%;
  right: 0%;
  border-left: 20px solid #535353;
  border-top: 15px solid transparent;
  border-bottom: 15px solid transparent;
}
.error .container-error-404 {
  margin-top: 10%;
  position: relative;
  height: 250px;
  padding-top: 40px;
}
.error .container-error-404 .clip {
  display: inline-block;
  transform: skew(-45deg);
}
.error .clip .shadow {
  overflow: hidden;
}
.error .clip:nth-of-type(2) .shadow {
  overflow: hidden;
  position: relative;
  box-shadow: inset 20px 0px 20px -15px rgba(150, 150, 150, 0.8),
    20px 0px 20px -15px rgba(150, 150, 150, 0.8);
}
.error .clip:nth-of-type(3) .shadow:after,
.error .clip:nth-of-type(1) .shadow:after {
  content: "";
  position: absolute;
  right: -8px;
  bottom: 0px;
  z-index: 9999;
  height: 100%;
  width: 10px;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(173, 173, 173, 0.8),
    transparent
  );
  border-radius: 50%;
}
.error .clip:nth-of-type(3) .shadow:after {
  left: -8px;
}
.error .digit {
  position: relative;
  top: 8%;
  color: white;
  /* background: #07b3f9; */
  background: #ffdf65;
  border-radius: 50%;
  display: inline-block;
  transform: skew(45deg);
}
.error .clip:nth-of-type(2) .digit {
  left: -10%;
}
.error .clip:nth-of-type(1) .digit {
  right: -20%;
}
.error .clip:nth-of-type(3) .digit {
  left: -20%;
}
.error h2 {
  color: #a2a2a2;
  font-weight: bold;
  padding-bottom: 20px;
}
.error .msg {
  position: relative;
  z-index: 9999;
  display: block;
  background: #535353;
  color: #a2a2a2;
  border-radius: 50%;
  font-style: italic;
}
.error .triangle {
  position: absolute;
  z-index: 999;
  transform: rotate(45deg);
  content: "";
  width: 0;
  height: 0;
}
@media (max-width: 767px) {
  .error .clip .shadow {
    height: 100px; /* Control */
  }
  .error .clip:nth-of-type(2) .shadow {
    width: 80px; /* Control play with JavaScript */
  }
  .error .clip:nth-of-type(1) .shadow,
  .error .clip:nth-of-type(3) .shadow {
    width: 100px; /* Control */
  }
  .error .digit {
    width: 80px; /* Control */
    height: 80px; /* Control */
    line-height: 80px; /* Control */
    font-size: 52px;
  }
  .error h2 {
    font-size: 24px; /* Control */
  }
  .error .msg {
    top: -110px;
    left: 15%;
    width: 40px;
    height: 40px;
    line-height: 40px;
    font-size: 18px;
  }
  .error span.triangle {
    top: 70%;
    right: -3%;
    border-left: 10px solid #535353;
    border-top: 8px solid transparent;
    border-bottom: 8px solid transparent;
  }
  .error .container-error-404 {
    height: 150px;
  }
}
</style>
