package year2022

object Day04 {

  val test =
    """vJrwpWtwJgWrhcsFMMfFFhFp
      |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
      |PmmdzqPrVvPwwTWBwg
      |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
      |ttgJtRGJQctTZtZT
      |CrZsJsPPZsGzwwsLwLmpwMDw
      |""".stripMargin

  val input =
    """lvcNpRHDCnTLCJlL
      |RFZggsMrjTFGCJmdmd
      |srsBZgBqwBqRZbzqtHpzzDNtHDqV
      |CCTPpCvlpzzZQQQflrzbQDttTJcgcggJcHtcddtdhT
      |nMLBRnGsFFLznRFRLMMNBnNLDRDdhScJccctdSccJJgDDHhH
      |GVBGVBsLjsrrvfzpjpfQ
      |dzVRSPVVBVDSPzDBQVSQFFlrclMplpMJMtPJlJvHZCMt
      |TjmGmbhjTnTmwhmrvvrHcZvCHZMl
      |fnLwwqfwfqjghHwGThwfTGGBFVDFFsszSRVzRBsdBDgFsV
      |CCWFCcdDWwcWFpSvggnzRRQszngJwT
      |mGtqqLrqfmmLNtNrgTjgJzNQlvJTvznJ
      |tnhVbhMLLZZrnWHPSHDBWbWBFd
      |nQhvgnCQjSSSTTSMCsLDsfPfDlsPJMWLzL
      |qrqBFFBbrVRLszLfsqdqPW
      |bNFFRbBcFZNrZRRRbprNpFrHSwznTnvSwgHvzCSSSjnCQwgz
      |tnnZZVmwmqtvVdZqnddQQHHTHQLsFTnsPrrgrQ
      |MzMflMGpzGzPGPgjLgHrGj
      |zPfhMJDDMJfzlhcRJvVwcVtwVcmcbqqtbv
      |GVzrBVcPVfGrzVVBcQJlGGRCZSSRtSdRnGLJ
      |wbjvHWbLvhFppjZdtwZRNddtJwlR
      |pvMmbpFFbqqqvWHMFvzrLDMMrMTTrVTPzVzc
      |qPmgpmwpwqWWPHdjdTNStzNLMztSWtMNtz
      |lVFfJrFJbbcsvcRVRZzQCzQNSZTZ
      |DGbvFSDGbDjnqgjwmGdq
      |DMnpnpwwnpmRRmcRBDnDwpbRQHssHqhHCHHSsQddHZQQcqqs
      |JlZjjlJgNSddfsgQdf
      |GvrWvzNjvPVLDpbPZwBP
      |drQDzHsHrdZWqDSSPwmmJDDbvbSJ
      |hphBhCMFlBtBtGTJMJsscPwTjMJv
      |tlBCGFgVFNpGClFFVGGtFBZrdZQznfdQQrRWVRQdRsVf
      |NjdCLdjzzlNdjwBBtZqpqPJQbN
      |CsDWcHcGHtcBbJPpbP
      |mGHSssSgSsHFSgGrSgmlLzCdldllrCVCLdnfnT
      |rDLLzRmbcLJRtRSvSBdZtSTp
      |MFswshwgsCsjghgFBsGssjlZpfpvdSHfTdCZTSpHtfddTH
      |llwlwGjMPMQQnBMswsFgglPVcWcDcbWqLWbbLJVDzrqnVr
      |pqmmcSTLfSSSMFlf
      |rHWtPWnHtlrlDntzWwtBFdzCFMRCfjRQFfgMRMjC
      |PWWHDVZPDDJVlWHncGGbqqTVvVmpGTmm
      |wLBtWhGWJBdMmZMs
      |jgvNCFvvGppGnmNJ
      |DDRQTgcvjTPFqGHhRVhLRSVL
      |tPPwLpBpDpgLSPvgQCvsLPjdjNZrJZsdZjsrsnZNjbZc
      |lMWzWMBhmMhRGfVRffHmMjJcJjrNNZnjJcWjNqJnZJ
      |mFMzhTmBGfHTwgPgtptFpPgP
      |qCcqJQHslgtsQsCZmPWNSRNZTPBBCN
      |nnLpjjnvwwvDnrGwFvbFjwPgPWRTPrrPShNhmmNSRRPN
      |bnwdDLjnzGgvFqdJQcqVfQVqHt
      |DfCzDCCTDLDBCsdjzwdrHjbRgjGH
      |MSStMScccJtPptJNJZtJJSrFdwPGjFFHHwsggrwdwRdP
      |nStlpVlhhNSshZlcNZnMcctpBChWBQLqCWqmqvmBCBmQBqmL
      |RfLHNvfLfLZQBtRZsBfffjVqGvqpGSmJpgrJpjGjrp
      |FDbPCMzbTTDDPmzrVzqppJBrBj
      |DPcWPWDhlbCcWBsQZZfHtdwf
      |fbHfPfHHfPZWgZfSGpqNBqdBBjpjdPBJqv
      |rnVNCwwrhhDrmmvcmjdDqcmB
      |hFRrslFRNhFzVthllRCRCCwnQtSGfQgZZbbSWQLSSTZWbQTt
      |nmVqTFCmTVbnvVCnqwFrffjhZLffhNrNJF
      |StBHWDgMBpHMBHDzLjffjWwZJNNfNZjL
      |wBBcDcgzdVbbQcnQlq
      |MfGCtqGDhjDqHhrjGCcJZZBwHRcspZsBsHRc
      |PFFpLFSpzVdSTPgnzzdPPZBRcZBwBJRcWJBmJW
      |vdTTzVpNVpfCChMGqMvr
      |VtZzBzhtlrhznFlBfgrfZgFrPjGRMGjRTmSjRjRTHjfRHmRv
      |DQpnsbJCsNNnpNNJsDQdCDcRmHPGTHTHRSmRmHjvjHSpSS
      |cQbnQdNLdJJQJJJDJWnFwzgwBthrgZBwBgFLZV
      |VhRRgmhpFjFFBDVPGPWQPzvvMMWfjf
      |qcnbnCbfLqJrCnrcdbbLrGSlzWsQvsWWzvWGdMWGQl
      |bnfbwrcwCrHqnHcZhFBTBVRDFmpBHB
      |lrtqltJJJqSTWJqVHRnsRhphdbfbzBdhsRsd
      |vSZCgZMMLSNvCQLPLDPNgZgnznzBfsGGnQnQGdnsfhsfzb
      |CMFLgmPgFFNMFDDCgLLcrWrjTTjtmSJqlWTTwWSr
      |LdjljBdZMFdZFLLLgPvWzQRzCsCmCVssmFSW
      |TJttwDhnnTlWsQzSQQDvWm
      |HtcnfctJwtwrHhrwhfHhJpjNLMZBMgZLrBlbbLNPNj
      |qqhNchPdpqTTNqpDmmvvGzVfzfmvdH
      |cwccjsFwFjnwGwQDfVVVVv
      |FbWjcRsLLFngBrjpbJqCJZTbJZNClq
      |lhznMTSzSnjhQGtVPQBdGB
      |msfNDDJLWslJgfNgCrmLdtGQFVvdGQPZVttBFP
      |RCrJJJDrJRsfgmbsrNsrlDMTSMHcjqwzScjMqqTjbbSc
      |nNgsvNWDRvgnRNVCFddTNZTNZQCTFZ
      |lffHJfHSPmSfvLlbLmpZrCTFTtrTQHqtTrCCrq
      |cpzblplpbvMzWnsDDB
      |CgtvQvJvMtWttvwftCdWvDQrfsFcrqnlcnqZZFRcRqsnhF
      |HzLzVBNLjHqnhzFlWFlr
      |NmBjLbVVbmbTLpTjBNVLHNdCtTSWQvCgdwSwJtWQwdSD
      |lncHcnlccVSLNSQNslncLcrZJCrgPfJZDrggJCCvZPHC
      |jRqqRmmqFwRFppfPPppPBfpWBvZf
      |wmMqjtTdjFwGGdtNhQbVfhntcNLVbL
      |HFBgMjpbpddMpbHdgHLLRNwhwFLDtNSRDLLD
      |zsCnfqZflrlnhhrtwNgggNNL
      |CGqnQzlqlWWMWgVBGg
      |pQnvzjztpzpCmtzzjzpnBHrJNGlqggMMqgqlNWgfNNqNCP
      |sVTSwddRRDVShwRwRTWgPNqMGQMGNqMWslsg
      |hDSTQhcQcHrtcBmZHv
      |QRmQfvQpWpswfZWWvNbhlMglgFbZDldlbL
      |rzHqtcnqqVjqjGcHdLdFdCFdCbLDnMCh
      |DGcGGSPDpWTsSfpv
      |llfMHTmvHlfZlFZRzgQzsFBLtLzFGF
      |wrWNJrdJhRmhGmNh
      |DWrrJjwPjCdPDwdmwnrTZnZZcqZfnqbvZfHvql
      |mPmVJJmNZJmlVBPPrZpWcFWbGWbjgqNbdqjSjg
      |nMhzwRhwvhMDMgWHRdGHgccggd
      |sMvnhQshMwwvdvMMCwBtlZtplZpTmPBVZVlC
      |ltlRzpncRglplzhFwFwzZZMWLWZBqnDVZLDVZQQQ
      |SJcdvJscNSsGcSGCSJmsTQDTVZQTLTQQWCTTMQCW
      |sPdJmcvsJvGJmdJmfpwftfrlztrRlPfP
      |LdPrWcMCWCfPdMJgdFsbRRHsRSHRHHcFpH
      |hVVTQmQTnRFLFsmzps
      |qthVVwZqlQLQhNttDDDWrffDJJJDrgNP
      |BTjTNjtlPrBjjrljbnMFfhVWFFhlMWMfHdll
      |mzcgZvDggDDCJCZLvsLJLcmVqWVSVqFLfdHHMWVWWWffnF
      |cmmcmzQDZQJmZCnDRgQCTTwjpTtwRjrbNjpPpwrj
      |rMbchQphhCSbGnzSbl
      |qFtgvTTqFFFFJGzWJG
      |NZjGqGBNjNHQrhpPNHQr
      |LnLmbtTtTwtLcVfFFLtPrfPrfqqqsqhSvrhrhh
      |BzJWzZRZJzJvlZJCZgZZpJHCqQDhNQPDqDDrjNsjPPNrhSCN
      |lpJWZzJpgHWdWMgHlJMZzgpJLGVLGGvwVwtmcGbvGMGVvncn
      |WdBgdqRgWqHmNNwsGgcQ
      |ptPVbPbSbMJrmsVzRzhwmcGQ
      |SSbvrJbJtCDZfTqdRfCdBZ
      |WDNNWvPpvNJRRbGLsGMnnbmG
      |qgFdBwgVdjwdtjjdBgMgGmLQsnZrnZssGswsmLrw
      |FqTCTtqjdjVqgCqSMJMTvPThTJMNDh
      |brSSSpZjVVWdfVrHPhRBggNNGwHr
      |fDlLzFCLMvnMMJLNHNCBQwNhRgwRPP
      |MJqMmfzDvFtLDtmsVsZZTsSScWcsbq
      |CSZlllhSdnDrrDdJjqjzbSGzGvwbfHMb
      |gTNvVNLQtsFpQHqfwfBfVMfHzf
      |QgNmWTtmTcmmdmrZRvnlPl
      |SmzfvfjvjbjLNJjD
      |cFhWMhGHTPhccMQQGBTFGwbVVwdbddJDvVJLvDDHvd
      |GFMBGcWTWhcGrhFZTTchQsSfgtmnnRvnmnnRgRCrRS
      |ZTQHVZsZSQpQQGBMGqfBRbRB
      |CwtLDtNFcPnllwnqvMgbvGVfVfBG
      |VClLWWFPPhlhctsTrrSpWpmszjZj
      |HChzPltNnnHtnpqSpHpFpSfSvS
      |mJmQssZJLdTQLcbjlGLGfSgMbqwwSFSMSFMMqMwS
      |JdBLlGTjLjjjjdmmBPnRzCBRNPhPtPWPtr
      |FPLHMHqqPMgFLLggsMghTJhwtDSSJDltJvtwdvST
      |WQfmjQZsjfZNQCrZCNZQQWQBCSClvdwTSClwSwlTJvtwJdbT
      |BpmzrWcpBrfmpsGPGFqPRgzqVPLM
      |bHjccpHwGHJTfPlffPwr
      |VtChMZVhhStZdfTCfJvcPRCTJn
      |sLNLZcdNZZqZqqVqSNWtjQDGHssHGHgQHDBgmsDg
      |CdWgCpddwgClFlmmVTBbRtRtbntBVVds
      |vcJGhPLPhJvChLhMLfccrvfvsVVbsGBTTBnVbRzBVstsGnbz
      |vJJHhjcCLPPjQPHLrSZmpgmqwlWZZgZZQm
      |VpTFCFtrjCdJdjHVFnSjszSllDjsDzgvzl
      |fhmhZBMtfZfGBNfNcmsbZnzSlRsRggslsbnv
      |qPLPhMcLhPfNWPpFrdFdFTtJ
      |nlgQJhJFlncMzMWZMFvw
      |mDdsDfHjHsjHdjTLfpDsbDcNzzwcRbZNZPMcCPWMRPMc
      |sqqdwffHjTmdmpffmLddTTGDnSJtJBShVVhrGVJtShrlBBnJ
      |CrcMcMDBCmLlZdSd
      |qPjGjnQPqWjgZmTdlFwTmqLJ
      |bnPnnzHjbPznzVpdpVDcvprr
      |TCScMQcQCrssJPQhQs
      |VpfnqqfdVVwpqvqwGbDPPsjgPShDSsJhlnSl
      |qffdmGpfwfbfvVqpfwwfbdqRMTSTWNMWTmZLTzTCZTMLWC
      |QQPpPbPbDNplSJrCCj
      |VdMzffgnRmVdfVWRvlrCTjRlNBvrrlrr
      |mGgNdthhGgMWWtsFcHcHwqLqtH
      |HrPFVqVppVpDjFDrVbCpDFJSLsmwjhjGLLmthJLJLmZs
      |WWgRdMdRMnQnRzWvPSssPWssJmhsshtG
      |MnfvMlnQccvfMlcTRMQdRfpHPDDDpPrDTbTBNbHbHDCq
      |GWWRsSwLhWsRsSbsPttThZqrNBJJBgPNCJCCqNMNgP
      |HpVDTHzfFDpFfzHzFVcrBZCggMJBvNrNgcNNrM
      |jlVpVpVVQDHdFVlmmmQTlzpjjGstWLsSbsnnnStWLRhnht
      |prLMDDjNCLZbdFLGngdLBv
      |VQHmhWSSzhWHmPJRJhSmVHJPFvgTbtnTbBtGqbQnbdqgTFqG
      |RzwzzhwhwNCvvfpc
      |wQgmZnhmWVtwQmnnnQbQhzwsFcRPrFPvRJhPlPPBBBFvJv
      |DdjqMMGLLMMGqTGdMqdMLdBBJsJPJBJJrrBFcqRlPlqr
      |DsddsfMsWgfzftZb
      |lcqlFSFwBBPlNwPlvSlQfWsVLTQjzjWVfLsWVq
      |HMMbMHMtJHgFzzFrVVtfFQ
      |RMFpCDDFcCNBcZvP
      |gwDrClhppDDPwPhnmPlwDrlDjMFfMTjMTjJmRHHJBJRMJHGj
      |LbbZBSvSLVRHffHJHJGZ
      |WztdtLsSvNQStbbtzdStthWhnwnPBDclgwwnrwllCC
      |MnMMBppMBDWMhpnCDBgCBmRbstvPvvbGltSPVGlVPWVv
      |TrrddJHjNcTqrrqdFcqZwSvLSlGGPbtFRbLvFVSRPG
      |JTccqTcwNQcTJrZwNJcJqHwJBQDfhCBCpCQpmpDfMRfCfBpn
      |njVcjHfGjVjpTCpMWprW
      |tsSsQDvSqQshDhtmWpnQnmMmbrpdzM
      |FNhsDDLNLnNllBqfRJGBVHBPHRRBZZ
      |hFVdlFSFlfZdRhgWgdWnnnfGpMNfnLMQzQQjMD
      |RsrJRHsvBcvHBHjDMMpDQDjjzDHj
      |BcCmBqvrbbqJgmFZtWdRVSVV
      |FzzdDJrJCFSFRqLlwsgspsBCpL
      |HQdWhMZMVwqLMllw
      |bbQtcvZcmHtNPZcWthWRvrdrRzrSDfRSrzjJjR
      |bTFZzHjZNJHzLggsJgbdsWcdcShWCwsSSdvGvv
      |VDBmntntfCBGGGGhRc
      |fMnnPDfmDlmnMPmtmttnVlHzZzNLbFbLbhzJJjMgJFbb
      |GzgJGPRrMSgTgpgH
      |hcvWhBdhcfPFvmFQvwfbHMsMMbpDpTDSSHsHpd
      |LmcvFFlcWQlFlfPnRZPVCJzJClCz
      |DdCHCHrmHRgghTHH
      |pFVZFwfssMsgghML
      |tSnphvhtctSSQNDqNdmrWGvq
      |rqmtRmGmcWrRRQprRRnfbGMMlPGGPblwMbTP
      |BHHhVZSvDNdhvBVhshbzfPbTmDfnwPwzPgbl
      |ddvBsSSdsLdshLsLpmWqcWrCCrtFpQ
      |wZPCwdPCHrnLQCGZDcPRqllzqqBzjlqc
      |gMmgnJspsvTmWNVWNpTNWNcDcqVjqDcclhSzllRSDzqR
      |JnWsgMnngmttFWWMdrwCZwHfZfdfGdFd
      |wwgNgrsWvbfBrqqsWbjDCDDDCDCmFbSmLDLlSC
      |QdpdzQTVdzRMTVVzcHTQLnlFmZHPSChCmlDPPHnP
      |dRMLVttzzVtTVQVqrrrgBtsvWWwtNw
      |vtBvntlqMvfnTfPDPhdRNbhdTFzF
      |QLWcmrrcgmCgCcsgcQWlWWrrDjjzzjsdFDdRhPNDhhhzDzPF
      |GGWHcCQcCCSlmmBVMGVBfMqwJtqv
      |cfqfhDRwhqZgRgRzRvcfhBSrsBnrDBBJWrnrWrSmmr
      |VCTVjGCTCjFddQntmrsVsJvrtrrW
      |PFQGpFbvPRMNqgRq
      |MmDgZZGMjZGfZRFztzCtCjzSrF
      |cBNpPJpBdNntcBHBccJlsSVVzzSwlCRrCnzsFw
      |PPBJLPPBBLPHBNQgqfMQtmTftGGvhq
      |bbZnbnVVgVSnbgZtntZrltsprpMCJvpqdJmsCMMmvvCq
      |BjDcjLLDzNjLDcjDzhcDNLLLHdpmpHJsqsMMNfCHfJpspqvp
      |dFLTFBcBzjFLgTbQtRgTVTlZ
      |nqNnrBRjLnjLZCqGGlqSGWlWDS
      |mTJTTcTJJfJfhhhwMbQDPWCQFCRlbDCSDDPl
      |dhMcRgJmgRrBrrrNgrLZ
      |GvJvJSGZFrGmmbmCrWnhjncLctcWttVqjLBB
      |wDlTzwlHTncRTqnRBt
      |gspglgzDzdPDfpgfdDzsgMPGvZJBrrbZGJNFmCFvmFvFvM
      |RLjMZZzfvNLBdjQfBfQdhRfSTVlcVqGbGcLGlbmqLVccmm
      |FggHCwsggrWWtCHJDDHtWrTNNlqSnlTlnGVTmWGcbcbm
      |PtpttrwsJssPsdRQvphZNzdMBh
      |NqqpZBHqTBpPNpPpGwwMPGTJjjLjQljGmtLfftllbJQfGf
      |nHczcrSFnVWSlrltrgJmjLLQ
      |SvzcDDVVFzdzhndCFSvnhcDspRDRDMPpMHRNPPZqppwM
      |FRSbVCSFFCDMFjRMjSSVFSWggMmWtWngJWttWmmJctnt
      |BPwcQQcQqQmWHfgrfwrh
      |PPlBQdNQvdLzzvclczdNRSbpLSbRbDjFZFFZVsFs
      |wtrrVhBbpcZSSjBfSfmm
      |MDWTvTMGMRCDCTQWsvfrRjjFfHlFmjlmLlHl
      |gQrTQvQDssdNWGsTstcbptwVPqcbpNqttP
      |jtGSwGQczrzjtGzrcsJwMRqMVMwRVMWFvVTWFV
      |DhLgnDLndDHmLvWqpTHqHHVNqF
      |LhdmPhfgZnZDlPCPmDfljQtGsJtBsWjGJzJSWBjC
      |zHDjcjBjTfjjfGpf
      |NNFTnNwPNNdqnJdFnqqTgmgftfftrWCZGbmrpWttmW
      |FLJqVNVNhnwnTRsRQBlQzShs
      |HJGJGJzzHHQHfJHsnNsGMbccMrTgbr
      |vddSCCjdmVvDDmvmBVbbBchcrrcscMTTnscn
      |VjWdFCVVMWWmjdSVFSVpqwwZttfJJltJZqltppLw
      |SnmPBPBnMLnPBsSgSDqRNRRccDfNcNQQRg
      |lZVWtWVzCjvZzCCGzDwbwRwtqJwJNTtDfD
      |zCzHZFFFfdLnBfFf
      |NRBFpNNJgNbWbJLRpRbWNtNpZllCZdjjZfjPVljTVCZQltlV
      |sDqHmsHcDrwHhMDlfCQfVBjDPCTd
      |MnGGcqwhhsrchcmGpzRJSSGGJWJzbJBR
      |LBzjQQzcjWvHWLnVDdnHRffHDCVR
      |rmJSrPJJsbNZssGSPrFpddfGwDRRpVjVpCGdCp
      |mPsrmsNTrPNLTjQlWQhqLc
      |MSDFszbhbRRTRdwhtw
      |PWmCZCmZVvGqMcjmJRpdTTtdLpqwdlll
      |MWGmmCVHvMSBDSNbbHHS
      |mBwSBSfSPHZCLPZSWwfPppTndVpdVncFgcgPpP
      |rhQJjzQjrltJzGqCrGJTvgqRpnTgFgcFTVFqFR
      |QbhGMJrhzrhQGQCsjwMDNWBDSZBZwSBLmM
      |bQfDPgDQbQNGPgflWfvMZcRMMFmcvMfZ
      |BLqSssjnzpBwszqwzFCNMvzWvRvzCCZFvc
      |BpNpjnNHnSpssqLqrBLLHjdhTPPDgbggllldhTPdrrQh
      |CvCMqNWVVqqPvNvvChhhdSnFHwBdWwhfdS
      |gqTZGGjlmclrZjlmSndSDwfFhhDBHm
      |tRrZpgrcctbbltRpgtqCVJCvPsRvsvPCQPCLPz
      |dTjRdWDBRzvjfzTfvTJPtJttsSLqHsSQJw
      |hrJNmbnFNZrbhlCsqltqcQcQSQqwPL
      |pVNhFgZphZmjzvjGDJWzVJ
      |gWzQhCWbQnCCFgCJnFQnWCzwjrHjjHGTwHGrhLwjjjtStL
      |splcpqDNDqqcZqRlspwHbjVrjjHTrwSbtVNV
      |BpMslDqDmRDRsBRBJPPnbzCfvQgmCWJb
      |tRtgRQWCwlTglHZHTglCtTdbbfvhWpbSBbhWzzbfGpfhbb
      |cqZVMJmLqmNrsJMDbzGrGSvzGBhvvG
      |mmnJPMZcclFRdnQtCQ
      |QVQVqfFzVVQQrQwZsCTrBtTrccTtctcJRRjT
      |vNNPnvGbBtWBLvBf
      |mMHbfDfHdHGmnhDDqZFDzVSQzF
      |NNlTNFCRTrfllTZsPWSsFPfzJdVQVpDQVszQVtpbzJMVbJ
      |LNHjNHjmLLjNqvGgvVQJQDVLbDVDpdQQzQ
      |nqmqGHjwgHvgwGHjGgccNTSWrrlCZrFfPSFFCP
      |qWzCQqhPCHjHmqJhqvqmjRgSFMTFggMFTFVRVVTgTm
      |SptGsDlnGfnDLgTMTwgRFFFs
      |bBcntZdpGZZcctlGtDfnDnBCSWqJvQhqjqzjhJqJQCQWPd
      |SjZJrSSDShddqLvPqzzdwq
      |nTssfRpQQmQCHlPBBgGmwVGzwm
      |TWQsbCRQFHFWQRTpzRHRsRrMtDrjhjbtMbccrttJjJht
      |cCChVMwPPMHCPCCPrvJnntdTJSvTtdrSRt
      |FGfFDBhGGlfGGfWJWdbSRSnRNbTvdn
      |fGpGlDmBhflgfDFmmfFpcVzMzqZZzcCPQVZzqP
      |SmgtSjGPjppBjbqqWTCZDQTHHHTg
      |VsFfCzLvsMfzNfNRhVMslzlHqrWrQDQcqDqTrLWHcrWJJW
      |dsRdsNCvNMVpwPdnnGbbPb""".stripMargin

  def main(args: Array[String]): Unit =
    println(part2(input))

  def part1(input: String) =
    input.split("\n")
      .map(split andThen intersectDistinct andThen toNumber)
      .sum

  def part2(input: String) =
    input.split("\n")
      .sliding(3, 3)
      .map(intersectArrayDistinct andThen toNumber)
      .sum
}

def split(s: String) = s.splitAt(s.length / 2)

def intersectDistinct(a: String, b: String) =
  a.intersect(b).distinct

def intersectArrayDistinct(strings: Array[String]) =
  strings.reduce(intersectDistinct)

def toNumber(c: String) =
  c(0) match
    case c if c.isLower => c.toInt % 96
    case c if c.isUpper => (c.toInt % 64) + 26
