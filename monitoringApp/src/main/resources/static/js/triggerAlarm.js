function triggerAlarm() {

    const preElements = document.querySelectorAll('pre');
    preElements.forEach(preElement => {
        const isError = preElement.textContent.includes('"resolvedDns": "failed"');
        const errorMessageElement = preElement.querySelector('.error-message');

        if (isError && !errorMessageElement) {
            // Add the flashing style class
            preElement.classList.add('flash-warning');

            // Add a child element for the error message
            const errorMessageElement = document.createElement('span');
            errorMessageElement.textContent = 'Critical error: "resolvedDns": "failed"';
            errorMessageElement.classList.add('error-message');
            preElement.appendChild(errorMessageElement);

        } else {
            // Remove the flashing style class if not matching
            preElement.classList.remove('flash-warning');

            // Remove the error message child element
            const errorMessageElement = preElement.querySelector('.error-message');
            if (errorMessageElement) {
                errorMessageElement.remove();
            }

        }
    });
}

setInterval(triggerAlarm, 500);