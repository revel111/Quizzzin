function startTimer(time) {
    const timerInterval = setInterval(() => {
        document.getElementById('timer').textContent =
            'If you haven\'t received an email, we can resend it in ' + time.toString() + ' seconds';
        time--;

        if (time < 0) {
            clearInterval(timerInterval);
            document.getElementById('timer').textContent = '';
            document.getElementById('resend').style.display = 'block';
        }
    }, 1000);
}