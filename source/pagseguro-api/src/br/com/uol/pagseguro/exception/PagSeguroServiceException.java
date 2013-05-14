/**
 * Copyright [2011] [PagSeguro Internet Ltda.]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package br.com.uol.pagseguro.exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.uol.pagseguro.domain.Error;
import br.com.uol.pagseguro.domain.HttpError;

/**
 * Encapsulates a problem that occurred calling a PagSeguro web service
 */
public class PagSeguroServiceException extends Exception {

    private static final long serialVersionUID = -851440159015340831L;

    /**
     * List of errors returned by the PagSeguro web service
     */
    private List<Error> errors;
    
    /**
     *  
     */
    private HttpError httpError;

    /**
     * Initializes a new instance of the PagSeguroServiceException class 
     */
    public PagSeguroServiceException() {
    }

    /**
     * Initializes a new instance of the PagSeguroServiceException class
     * 
     * @param errors
     * @param httpError
     */
    public PagSeguroServiceException(List<Error> errors, HttpError httpError) {
        this.errors = errors;
        this.httpError = httpError;
    }

    /**
     * Initializes a new instance of the PagSeguroServiceException class
     * 
     * @param httpError
     */
    public PagSeguroServiceException(HttpError httpError) {
        this.httpError = httpError;
    }

    /**
     * @return the list of errors returned by the PagSeguro web service 
     */
    public List<Error> getErrorList() {

        if (errors == null || errors.isEmpty()) {
            Error erro = new Error();
            httpError = (httpError != null) ? httpError : HttpError.INTERNAL_SERVER_ERROR;
            erro.setCode(Integer.toString(httpError.getValue()));
            erro.setMessage(httpError.getMessage());

            List<Error> listaErro = new ArrayList<>();
            listaErro.add(erro);

            return listaErro;
        }

        return errors;
    }

    /**
     * Sets the list of errors returned by the PagSeguro web service
     * 
     * @param errorList
     */
    public void setErrorList(List<Error> errorList) {
        this.errors = errorList;
    }

    public String toString() {
        List<Error> listErrors = this.getErrorList();
        StringBuffer errosMsg = new StringBuffer();

        for (Iterator iter = listErrors.iterator(); iter.hasNext();) {
            Error error = (Error) iter.next();

            String code = error.getCode();
            String msg = error.getMessage();

            errosMsg.append(code + " - " + msg + "; ");

        }

        return errosMsg.toString();
    }
}