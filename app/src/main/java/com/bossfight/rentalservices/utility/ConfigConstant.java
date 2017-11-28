package com.bossfight.rentalservices.utility;

/**
 * Created by sindhya on 4/13/17.
 */
public class ConfigConstant {

    public static final String BASE_URL="https://smart-street.herokuapp.com/users/signin";

    public static final String PATIENT_DOC_LIST_ENDPOINT="patient/doctors";

    public static final String DOCTOR_ADD_HEALTH_REQUEST="doctor/healthDataRequest/add";
    public static final String PATIENT_HEALTH_REQUEST="patient/healthDataRequest";
    public static final String PATIENT_HEALTH_REQUEST_STATUS_UPDATE="patient/healthDataRequestStatus/update";
    public static final String GET_PATIENT_HEALTH_DATA="patient/healthData";
    public static final String POST_PATIENT_HEALTH_DATA="patient/healthData/add";
    public static final String GET_PATIENT_REQUEST_DATA="doctor/healthDataRequestDetails";
    public static final String DOCTOR_LIST_ENDPOINT="patient/doctors";

    public static final String ADD_APPOINTMENT_ENDPOINT="patient/appointment/add";

    public static final String FITBIT_AUTH_URL="https://www.fitbit.com/oauth2/authorize?response_type=token&client_id=228BFB&redirect_uri=hospapp%3A%2F%2Fcallbackresponse&scope=activity%20heartrate%20location%20nutrition%20profile%20settings%20sleep%20social%20weight&expires_in=604800";

    public static final String PACKAGE_CUSTOM_TAB = "com.android.chrome";

    public static final String insertDoctor = "/doctor/addInformation";
    public static final String insertPatient = "/patient/addInformation";
    public static final String insertDoctorCredential = "/doctor/credentials/add";
    public static final String insertPatientCredential = "/patient/credentials/add";
    public static final String authenticateDoctor = "/users/signin";
    public static final String authenticatePatient = "patient/credentials/verify";
    public static final String DOCTOR_SPECIALITY="speciality";
    public static final String PATIENT_EMERGENCY="/patient/emergencyContact/";
    public static final String PATIENT_ADD_DOCTOR="patient/association/add";


    public static final int INVALID_ID = -1;


}
